package com.sudip.ecommerce_poc.vo;

import com.sudip.ecommerce_poc.entity.Products;
import lombok.Data;

import java.util.List;

@Data
public class OrderDetailsVo {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private String userAddress;
    private List<Products> products;
}
