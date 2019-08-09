package com.neu.management.controller;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.Message;
import com.neu.management.model.TProduct;
import com.neu.management.model.TUser;
import com.neu.management.service.TProductServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class TProductController {
    @Resource
    private TProductServiceImpl tProductService;

    /**
     * 根据product.flag或product.num或product.name来查询;
     * product=null,查询全部
     * currentPage,当前页面
     * ok
     */
    @RequestMapping("list/{currentPage}")
    public Message selectProducts(@RequestBody TProduct product, @PathVariable  Integer currentPage)
    {
        Integer page =currentPage;
        System.out.println(currentPage);
        Message selectProducts = new Message();
        PageInfo<TProduct> data=tProductService.selectProducts(product,page);
        if (data !=null){
            selectProducts.setCode(200);
            selectProducts.setData(data);
            selectProducts.setMessage("查询成功");
        }else {
            selectProducts.setCode(202);
            selectProducts.setMessage("查询失败！");
        }
        return selectProducts;

    }
    //查询id  ok
    @RequestMapping("selById/{id}")
    public Message selectProductById(@PathVariable Integer id)
    {
        Message selectProducts = new Message();
        TProduct data=tProductService.selectById(id);
        if ( data!=null){
            selectProducts.setCode(200);
            selectProducts.setData(data);
            selectProducts.setMessage("查询成功");
        }else {
            selectProducts.setCode(202);
            selectProducts.setMessage("查询失败！");
        }
        return selectProducts;

    }
    //查询num ok
    @RequestMapping("selByNum/{num}")
    public Message selectProductByNum(@PathVariable String num)
    {
        System.out.println(num);
        Message selectProducts = new Message();
        TProduct data=tProductService.selectByNum(num);
        if ( data!=null){
            selectProducts.setCode(200);
            selectProducts.setData(data);
            selectProducts.setMessage("查询成功");
        }else {
            selectProducts.setCode(202);
            selectProducts.setMessage("查询失败！");
        }
        return selectProducts;

    }
    //添加产品 ok
    @RequestMapping("add")
    public Message addProduct(@RequestBody TProduct product, HttpSession session){

        Message message =new Message();
        int resoult =tProductService.addProduct(product);
        if(resoult==-1)
        {
            message.setCode(202);
            message.setMessage("产品信息及序列号不能为空");
        }
        else
        {

//            if(session.getAttribute("user")!=null)
//            {
//                TUser tUser;
//                tUser = (TUser)session.getAttribute("user");
//                product.setCreateUserid(tUser.getId());
//            }
            message.setCode(200);
            message.setMessage("添加成功");
        }
        message.setData(resoult);
        return message;

    }
    //更新产品 ok
    @RequestMapping("update")
    public Message updateProduct(@RequestBody TProduct product,HttpSession session)
    {
        Message message =new Message();
        if(product!=null&&product.getId()!=null)
        {
//            if(session.getAttribute("user")!=null)
//            {
//                TUser tUser;
//                tUser = (TUser)session.getAttribute("user");
//                product.setCreateUserid(tUser.getId());
//            }
            int resoult = tProductService.updateProduct(product);

            message.setCode(200);
            message.setData("更新条数:"+resoult);
            return  message;

        }
        message.setCode(202);
        message.setMessage("产品信息不能为空");
        return message;
    }
    //id删除, ok
    @RequestMapping("del/{id}")
    public Message deleteProduct(@PathVariable Integer id)
    {
        Message message = new Message();
        System.out.println(id);
        int resoult=tProductService.deleteById(id);
        if (resoult == -1){
        // id为空
            message.setCode(202);
            message.setMessage("Id不能为空");
            return message;
        }else {
            message.setCode(200);
            message.setMessage("删除成功");
            message.setData("删除条数:"+resoult);
            return message;
        }
    }
    //批量删除
    @RequestMapping("deleteList")
    public Message deleteProducts(@RequestBody List<Integer> ids)
    {
        System.out.println(ids);
        Message message = new Message();
        int resoult=tProductService.deleteProductByIds(ids);
        if (resoult == -1){
            // id为空
            message.setCode(202);
            message.setMessage("Id不能为空");
        }else {
            message.setCode(200);
            message.setMessage("删除成功");
            message.setData("删除条数:"+resoult);
        }

        return message;

    }
}
