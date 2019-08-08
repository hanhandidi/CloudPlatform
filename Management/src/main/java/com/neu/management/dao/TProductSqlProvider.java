package com.neu.management.dao;

import com.neu.management.model.TProduct;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class TProductSqlProvider {
    public String selectProducts(TProduct product){
        BEGIN();
        SELECT("*");
        FROM("t_product");
        if(product!=null)
        {
            if(product.getProductNum()!=null)
                WHERE("product_num="+product.getProductNum());
            if(product.getProductName()!=null)
                WHERE("product_name"+product.getProductName());
            if(product.getFlag()!=null)
                WHERE("flag="+product.getFlag());
        }
        return  SQL();
    }
    public String deleteProductsById(Integer[] id)
    {
        BEGIN();;
        DELETE_FROM("t_product");
        if(id!=null&&id.length>0)
        {
            for(Integer i:id)
            {
                WHERE("id="+i);
                OR();
            }
            return SQL();
        }
        WHERE("1=2");
        return SQL();

    }
}
