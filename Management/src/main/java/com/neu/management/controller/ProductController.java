package com.neu.management.controller;

import com.github.pagehelper.PageInfo;
import com.neu.management.model.Message;
import com.neu.management.model.TProduct;
import com.neu.management.service.ProductServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Resource
    private ProductServiceImpl productService;

    /**
     * 根据product.flag或product.num或product.name来查询;
     * product=null,查询全部
     * currentPage,当前页面
     * ok
     */
    @ApiOperation("根据product.flag或product.num或product.name来查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="product",value="产品实体类",dataType="TProduct"),
            @ApiImplicitParam(name="currentPage",value="当前页",dataType="Integer")
    })
    @PostMapping("listPage/{currentPage}")
    public Message selectProducts(@RequestBody TProduct product, @PathVariable Integer currentPage) {
        Message selectProducts = new Message();
        PageInfo<TProduct> data = productService.selectProducts(product, currentPage);
        if ( data != null ) {
            selectProducts.setCode(200);
            selectProducts.setData(data);
            selectProducts.setMessage("查询成功！");
        } else {
            selectProducts.setCode(202);
            selectProducts.setMessage("查询失败！");
        }
        return selectProducts;
    }

    // 获取所有的产品 不分页 ok
    @ApiOperation("根据product.flag或product.num或product.name获取所有的产品 不分页")
    @ApiImplicitParam(name="product",value="产品实体类",dataType="TProduct")
    @PostMapping("list")
    public Message selectProducts(@RequestBody(required = false) TProduct product) {
        Message selectProducts = new Message();
        List<TProduct> tProducts = productService.selectProducts(product);
        if ( tProducts != null ) {
            selectProducts.setCode(200);
            selectProducts.setData(tProducts);
            selectProducts.setMessage("获取信息成功！");
        } else {
            selectProducts.setCode(202);
            selectProducts.setMessage("获取信息失败！");
        }
        return selectProducts;
    }

    //查询id  ok
    @GetMapping("selById/{id}")
    @ApiOperation("查询id")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    public Message selectProductById(@PathVariable Integer id) {
        Message selectProductById = new Message();
        if ( productService.selectById(id) != null ) {
            selectProductById.setCode(200);
            selectProductById.setData(productService.selectById(id));
            selectProductById.setMessage("查询成功！");
        } else {
            selectProductById.setCode(202);
            selectProductById.setMessage("查询失败！");
        }
        return selectProductById;
    }

    //查询num ok
    @ApiOperation("查询num")
    @ApiImplicitParam(name="num",value="产品序列号",dataType="String")
    @GetMapping("selByNum/{num}")
    public Message selectProductByNum(@PathVariable String num) {
        Message selectProductByNum = new Message();
        if ( productService.selectByNum(num) != null ) {
            selectProductByNum.setCode(200);
            selectProductByNum.setData(productService.selectByNum(num));
            selectProductByNum.setMessage("查询成功！");
        } else {
            selectProductByNum.setCode(202);
            selectProductByNum.setMessage("查询失败！");
        }
        return selectProductByNum;
    }

    //添加产品 ok
    @ApiOperation("添加产品")
    @ApiImplicitParam(name="product",value="产品实体类",dataType="TProduct")
    @PostMapping("add")
    public Message addProduct(@RequestBody TProduct product) {
        Message addProductMessage = new Message();
        product.setUpdateUserid(product.getCreateUserid());
        product.setCreateTime(new Timestamp(new Date().getTime()));
        product.setUpdateTime(new Timestamp(new Date().getTime()));
        product.setProductNum("P"+new Timestamp(new Date().getTime()).getTime());
        TProduct tProduct = productService.addProduct(product);
        if ( tProduct == null ) {
            // 序列号重复
            addProductMessage.setCode(202);
            addProductMessage.setMessage("添加产品信息失败，同一工厂产品不可重名，同一工厂产品序列号不可重复，请重试！");
        } else {
            // 借助get方法添加缓存
            productService.selectById((int) tProduct.getId());
            addProductMessage.setCode(200);
            addProductMessage.setMessage("添加产品信息成功！");
            addProductMessage.setData(tProduct);
        }
        return addProductMessage;
    }

    //更新产品 ok
    @ApiOperation("更新产品")
    @ApiImplicitParam(name="product",value="产品实体类",dataType="TProduct")
    @PutMapping("update")
    public Message updateProduct(@RequestBody TProduct product) {
        Message updateProductMessage = new Message();
        product.setUpdateTime(new Timestamp(new Date().getTime()));
        if ( productService.updateProduct(product) == null ) {
            // 序列号重复
            updateProductMessage.setCode(202);
            updateProductMessage.setMessage("更新产品信息失败，同一工厂产品不可重名，同一工厂产品序列号不可重复，请重试！");
        } else {
            updateProductMessage.setCode(200);
            updateProductMessage.setMessage("更新产品信息成功！");
            updateProductMessage.setData(product);
        }
        return updateProductMessage;
    }

    //id删除, ok
    @ApiOperation("id删除")
    @ApiImplicitParam(name="id",value="id",dataType="Integer")
    @DeleteMapping("del/{id}")
    public Message deleteProduct(@PathVariable Integer id) {
        Message message = new Message();
        int result = productService.deleteById(id);
        if ( result == -1 ) {
            // id为空
            message.setCode(202);
            message.setMessage("删除失败，Id不能为空！");
        } else if ( result == 1 ) {
            message.setCode(200);
            message.setMessage("删除成功！");
        } else {
            message.setCode(202);
            message.setMessage("删除失败，该产品存在关联已接单订单！");
        }
        return message;
    }

    //批量删除 ok
    @ApiOperation("批量删除")
    @ApiImplicitParam(name="ids",value="id数组",dataType="List<Integer>")
    @PostMapping("deleteList")
    public Message deleteProducts(@RequestBody List<Integer> ids) {
        Message message = new Message();
        int result = productService.deleteProductByIds(ids);
        if ( result == -1 ) {
            // id为空
            message.setCode(202);
            message.setMessage("Id不能为空！");
        } else {
            message.setCode(200);
            message.setMessage("删除成功！");
        }
        return message;
    }
}
