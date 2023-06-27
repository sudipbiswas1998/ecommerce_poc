package com.sudip.ecommerce_poc.controller;

import com.sudip.ecommerce_poc.service.ProductService;
import com.sudip.ecommerce_poc.util.BLUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/get_products")
    public ResponseEntity<Map<String, Object>> getProducts(@RequestParam("category") String category) {
        log.info("in getProducts controller");
        Map<String , Object> response = productService.getProducts(category);
        return sendResponse(response);
    }

    private ResponseEntity<Map<String, Object>> sendResponse(Map<String, Object> responseMap) {
        if (!BLUtil.validateResponseStatus(responseMap))
            return ResponseEntity.badRequest().body(responseMap);
        return ResponseEntity.ok(responseMap);
    }
}
