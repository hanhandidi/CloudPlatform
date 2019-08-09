package com.neu.management.dao;

import com.neu.management.model.TProduct;

import java.util.List;
import java.util.Map;

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
    public String deleteProductByIds(Map<String,List<Integer>> map)
    {

        List<Integer> ids =map.get("list");
        System.out.println(ids+"aa");
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM t_product WHERE id IN (");
        for (int i = 0; i < ids.size(); i++) {
            sb.append("'").append(ids.get(i)).append("'");
            if (i < ids.size() - 1)
                sb.append(",");
        }
        sb.append(")");
        System.out.println("99999999999999999999999");
        return sb.toString();


    }
}
