package com.neu.management.controller;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.TProduct;
import com.neu.management.model.TUser;
import com.neu.management.service.TProductServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class TProductController {
    @Resource
    private TProductServiceImpl tProductService;

    /**
     * 根据product.flag或product.num或product.name来查询;
     * product=null,查询全部
     * currentPage,当前页面
     */
    @RequestMapping("products")
    public PageInfo<TProduct> selectProducts(TProduct product,Integer currentPage)
    {

        return tProductService.selectProducts(product,currentPage);
    }
    @RequestMapping("addProducts")
    public int addProduct(TProduct product, HttpSession session){
        product.setId(null);
        Timestamp t=new Timestamp(System.currentTimeMillis());
        product.setCreateTime(t);
//       已登录
        TUser user =(TUser) session.getAttribute("user");
//        product.setCreateUserid(user.getId());
//        product.setUpdateTime(t);
        //product.setUpdateUserid(user.getId());
        if(product.getProductNum()==null)
            return -1;
        return tProductService.addProduct(product);
    }
    @RequestMapping("updateProduct")
    public int updateProduct(TProduct product,HttpSession session)
    {
        System.out.println(product.getId());
        if (product.getId()==null)
            return -1;
        Timestamp t=new Timestamp(System.currentTimeMillis());
        product.setUpdateTime(t);
//        TUser user =(TUser) session.getAttribute("user");
//        product.setUpdateUserid(user.getId());
        return tProductService.updateProduct(product);
    }
    @RequestMapping("deleteProducts")
    public int deleteProducts(Object id, HttpSession session)
    {
        System.out.println(id);

//        return tProductService.deleteProductById(id);
        return 0;
    }
}
