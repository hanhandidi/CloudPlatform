package com.neu.management.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.management.dao.EquipmentDao;
import com.neu.management.dao.EquipmentRepository;
import com.neu.management.model.TEquipment;
import com.neu.management.util.Define;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EquipmentServiceImpl implements EquipmentService{
    private final EquipmentDao equipmentDao;

    private final EquipmentRepository equipmentRepository;

    private final ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    public EquipmentServiceImpl(EquipmentDao equipmentDao, EquipmentRepository equipmentRepository, ElasticsearchTemplate elasticsearchTemplate) {
        this.equipmentDao = equipmentDao;
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.equipmentRepository = equipmentRepository;
    }

    // @Cacheable(value="user",key="#user.username",unless="#result==null") 添加缓存 配置在方法或类上，作用：本方法执行后，先去缓存看有没有数据，如果没有，从数据库中查找出来，给缓存中存一份，返回结果，下次本方法执行，在缓存未过期情况下，先在缓存中查找，有的话直接返回，没有的话从数据库查找
    // @CachePut(value="user",key="#user.username") 更新缓存 类似于更新操作，即每次不管缓存中有没有结果，都从数据库查找结果，并将结果更新到缓存，并返回结果
    // @CacheEvict(value="thisredis",key="#user.username",condition="#id!=1") 删除缓存 用来清除用在本方法或者类上的缓存数据（用在哪里清除哪里）
    // 缓存 调用方法的结果（返回值）
    // 利用insert返回的ID添加缓存
    @Override
    // @Cacheable 先检查传过来的对象是否缓存 对于新数据 尚未生成id，默认id = 0 -》key值被固定了 借助get方法添加缓存
    // @Cacheable(value="TEquipment",key="T(String).valueOf('TEquipment').concat('-').concat(#tEquipment.id)",unless="#result == null")
    public TEquipment addEquipment(TEquipment tEquipment) {
        // 设备序列号
        String seq = tEquipment.getEquipmentSeq();
        // 根据序列号查询（设备序列号不能重复）
        if (equipmentDao.selectBySeq(seq) != null){
            return null;
        }else {
            equipmentDao.insert(tEquipment);
            // System.out.println(tEquipment.getId()); // 插入成功之后获得主键
            // 保存文档
            equipmentRepository.save(tEquipment);
            // getEquipment((int) tEquipment.getId());
            return tEquipment;
        }
    }

    @Override
    @CacheEvict(value="TEquipment",key="T(String).valueOf('TEquipment').concat('-').concat(#id)")
    public void deleteEquipment(Integer id) {
        // 删除文档
        equipmentRepository.deleteById(id);
        equipmentDao.deleteById(id);
    }

    // 批量删除清除所有缓存
    @Override
    @CacheEvict(value="TEquipment", allEntries = true)
    public void deleteEquipmentList(List<Integer> ids) {
        // 批量删除文档
        // equipmentRepository.deleteAll();
        for (Integer id: ids){
            equipmentRepository.deleteById(id);
        }
        equipmentDao.deleteBatch(ids);
    }

    @Override
    @CachePut(value="TEquipment",key="T(String).valueOf('TEquipment').concat('-').concat(#tEquipment.id)")
    public TEquipment updateEquipment(TEquipment tEquipment) {
        // 设备序列号
        String seq = tEquipment.getEquipmentSeq();
        // 根据序列号查询（设备序列号不能重复）
        if (equipmentDao.selectBySeq(seq) != null){
            return null;
        }else {
            // 修改文档
            equipmentRepository.save(tEquipment);
            equipmentDao.update(tEquipment);
            return tEquipment;
        }
    }

    @Override
    @Cacheable(value="TEquipment",key="T(String).valueOf('TEquipment').concat('-').concat(#id)",unless="#result == null")
    public TEquipment getEquipment(Integer id) {
        return equipmentDao.selectById(id);
    }

    // 根据序列号不缓存
    @Override
    public TEquipment getEquipmentBySeq(String equipmentSeq) {
        return equipmentDao.selectBySeq(equipmentSeq);
    }

    @Override
    public PageInfo<TEquipment> listEquipment(Integer currPage, TEquipment tEquipment) {
        if(currPage == null)
            currPage = 1;
        //设置从第几页查询N条
        PageHelper.startPage(currPage, Define.PAGE_SIZE);
        //分页查询
        return new PageInfo<>(equipmentDao.selectAll(tEquipment));
    }

    @Override
    public Page<TEquipment> search(Integer currPage, String equipmentName) {
        //构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //添加分词查询
        queryBuilder.withQuery(QueryBuilders.multiMatchQuery(equipmentName,"equipmentName"));
        //分页(ES从第0页开始查)
        queryBuilder.withPageable(PageRequest.of(currPage-1,Define.PAGE_SIZE));
        //搜索
        return equipmentRepository.search(queryBuilder.build());
    }

    @Override
    public PageInfo<TEquipment> searchHighlight(Integer currPage, String equipmentName) {
        Pageable pageable = PageRequest.of(currPage-1,Define.PAGE_SIZE);

        //构建查询条件
        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
        //添加分词查询
        queryBuilder.should(QueryBuilders.multiMatchQuery(equipmentName,"equipmentName"));

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                //设置高亮字段显示的样式
                .withHighlightFields(
                        new HighlightBuilder.Field("equipmentName").preTags("<em style='color:red'>").postTags("</em>")
                )
                .withPageable(pageable)
                .build();

        //实施查询，注意：这里的泛型最后和 elasticsearch 中的字段对应
        Page<TEquipment> tEquipmentPage = elasticsearchTemplate.queryForPage(searchQuery, TEquipment.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<TEquipment> tEquipmentList = new ArrayList<>();
                //命中记录
                SearchHits hits = response.getHits();
                for (SearchHit hit : hits){
                    if (hits.totalHits <= 0){
                        return null;
                    }
                    TEquipment tEquipment = new TEquipment();
                    tEquipment.setId((Integer)hit.getSourceAsMap().get("id"));
                    tEquipment.setFlag((Integer)hit.getSourceAsMap().get("flag"));
                    tEquipment.setCreateTime(new Timestamp((Long)(hit.getSourceAsMap().get("createTime"))));
                    tEquipment.setCreateUserid((Integer)hit.getSourceAsMap().get("createUserid"));
                    tEquipment.setUpdateTime(new Timestamp((Long)(hit.getSourceAsMap().get("updateTime"))));
                    tEquipment.setUpdateUserid((Integer)hit.getSourceAsMap().get("updateUserid"));
                    tEquipment.setEquipmentSeq(String.valueOf(hit.getSourceAsMap().get("equipmentSeq")));
                    tEquipment.setEquipmentName(String.valueOf(hit.getSourceAsMap().get("equipmentName")));
                    tEquipment.setEquipmentImgUrl(String.valueOf(hit.getSourceAsMap().get("equipmentImgUrl")));
                    tEquipment.setEquipmentStatus((Integer)hit.getSourceAsMap().get("equipmentStatus"));
                    tEquipment.setFactoryId((Integer)hit.getSourceAsMap().get("factoryId"));

                    //设置高亮（若对应字段有高亮的话）
                    setHighLight(hit, tEquipment);
                    tEquipmentList.add(tEquipment);
                }
                return new AggregatedPageImpl<>((List<T>)tEquipmentList);
            }
        });
        Page<TEquipment> pages = this.search(currPage,equipmentName);
        PageInfo<TEquipment> pageInfo = new PageInfo<>();
        pageInfo.setList(tEquipmentPage.getContent());
        pageInfo.setTotal(pages.getTotalElements());
        pageInfo.setPageSize(pages.getSize());
        return pageInfo;
    }

    /**
     * 设置高亮
     * @param hit 命中记录
     * @param object 待赋值对象
     */
    private static void setHighLight(SearchHit hit, Object object){
        //获取对应的高亮域
        Map<String, HighlightField> highlightFields = hit.getHighlightFields();
        HighlightField highlightField = highlightFields.get("equipmentName");
        if (highlightField != null){
            //取得定义的高亮标签
            String highLightMessage = highlightField.fragments()[0].toString();
            // 反射调用set方法将高亮内容设置进去
            try {
                String setMethodName = parSetMethodName();
                Class<?> Clazz = object.getClass();
                Method setMethod = Clazz.getMethod(setMethodName, String.class);
                setMethod.invoke(object, highLightMessage);
            } catch (Exception e) {
                System.out.println("反射报错"+e);
            }
        }
    }

    /**
     * 根据字段名，获取Set方法名
     * @return  Set方法名
     */
    private static String parSetMethodName(){
        int startIndex = 0;
        if ("equipmentName".charAt(0) == '_'){
            startIndex = 1;
        }
        return "set" + "equipmentName".substring(startIndex, startIndex + 1).toUpperCase()
                + "equipmentName".substring(startIndex + 1);
    }
}
