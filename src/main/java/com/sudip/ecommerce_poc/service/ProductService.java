package com.sudip.ecommerce_poc.service;

import com.sudip.ecommerce_poc.entity.ProductMaster;
import com.sudip.ecommerce_poc.entity.Products;
import com.sudip.ecommerce_poc.repository.ProductMasterRepo;
import com.sudip.ecommerce_poc.repository.ProductRepo;
import com.sudip.ecommerce_poc.util.ResponseUtils;
import com.sudip.ecommerce_poc.vo.response.ReasonCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductMasterRepo productMasterRepo;

    public Map<String, Object> getProducts(String category) {
        log.info("in getProducts()");
        Map<String, Object> master = new LinkedHashMap<>();
        List<Products> productsList = productRepo.findAll();
        if (category.equalsIgnoreCase("all")) {
            List<ProductMaster> productMasterList = productMasterRepo.findAll();
            for (ProductMaster productMaster : productMasterList) {
                if (productMaster.getActiveStatus()) {
                    List<Products> products = productsList.stream().filter(i -> i.getCategory().equalsIgnoreCase(productMaster.getCategory())).collect(Collectors.toList());
                    List<Map<String, Object>> productByCategory = new ArrayList<>();
                    for (Products products1 : products) {
                        Map<String, Object> newProduct = new LinkedHashMap<>();
                        newProduct.put("id", products1.getId());
                        newProduct.put("name", products1.getProductName());
                        newProduct.put("title", products1.getProductTitle());
                        newProduct.put("price", products1.getProductPrice());
                        newProduct.put("image", products1.getProductImage());

                        productByCategory.add(newProduct);
                    }
                    master.put(productMaster.getCategory(), productByCategory);
                }

            }
            return ResponseUtils.successResponse(master);

        } else {
            Optional<ProductMaster> productMasterOptional = productMasterRepo.findByCategory(category);
            if (productMasterOptional.isPresent()) {
                if (productMasterOptional.get().getActiveStatus()) {
                    List<Products> products = productsList.stream().filter(i -> i.getCategory().equalsIgnoreCase(productMasterOptional.get().getCategory())).collect(Collectors.toList());
                    List<Map<String, Object>> productByCategory = new ArrayList<>();
                    for (Products products1 : products) {
                        Map<String, Object> newProduct = new LinkedHashMap<>();
                        newProduct.put("id", products1.getId());
                        newProduct.put("name", products1.getProductName());
                        newProduct.put("title", products1.getProductTitle());
                        newProduct.put("price", products1.getProductPrice());
                        newProduct.put("image", products1.getProductImage());

                        productByCategory.add(newProduct);
                    }
                    master.put(productMasterOptional.get().getCategory(), productByCategory);

                    return ResponseUtils.successResponse(master);
                } else {
                    log.info("Product category is not active");
                    return ResponseUtils.failureResponse("product category not active", ReasonCode.NONE.value());
                }

            } else {
                log.info("product category incorrect");
                return ResponseUtils.failureResponse("Product catagory not valid", ReasonCode.INVALID_PARAMETER.value());
            }
        }
    }
}
