package com.neu.management.dao;

import com.neu.management.model.TEquipment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 实现索引是对应的索引的文档增删改查操作
 */
public interface EquipmentRepository extends ElasticsearchRepository<TEquipment,Integer>{
}
