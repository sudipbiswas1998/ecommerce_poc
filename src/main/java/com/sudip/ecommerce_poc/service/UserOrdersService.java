package com.sudip.ecommerce_poc.service;

import com.sudip.ecommerce_poc.entity.Products;
import com.sudip.ecommerce_poc.entity.User;
import com.sudip.ecommerce_poc.entity.UserOrders;
import com.sudip.ecommerce_poc.repository.ProductRepo;
import com.sudip.ecommerce_poc.repository.UserOrdersRepo;
import com.sudip.ecommerce_poc.repository.UserRepo;
import com.sudip.ecommerce_poc.util.ResponseUtils;
import com.sudip.ecommerce_poc.vo.OrderDetailsVo;
import com.sudip.ecommerce_poc.vo.response.ReasonCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserOrdersService {

    @Autowired
    private UserOrdersRepo userOrdersRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;

    public Map<String, Object> getProductById(Integer orderId, Integer userId) {
        log.info("in getById()");
        Optional<UserOrders> userOrdersOptional = userOrdersRepo.findById(orderId);
        if(!userOrdersOptional.isPresent()){
            log.info("order not found");
            return ResponseUtils.failureResponse("Order not found or invalid order id", ReasonCode.INVALID_PARAMETER.value());
        }else {
            UserOrders userOrders = userOrdersOptional.get();
            if(userId != userOrders.getUserId()){
                return ResponseUtils.failureResponse("Not your order", ReasonCode.NOT_FOUND.value());
            }
            OrderDetailsVo orderDetailsVo = new OrderDetailsVo();
            orderDetailsVo.setId(userOrders.getId());
            orderDetailsVo.setUserId(userOrders.getUserId());
            orderDetailsVo.setProductId(userOrders.getProductId());
            orderDetailsVo.setUserAddress(userOrders.getUserAddress());
            List<Products> productsList = new ArrayList<>();
            Optional<Products> products = productRepo.findById(userOrders.getProductId());
            if(products.isPresent()){
                productsList.add(products.get());
                orderDetailsVo.setProducts(productsList);
            }else {
                log.info("Product not found");
            }
            return ResponseUtils.successResponse(orderDetailsVo);
        }
    }

    public Map<String, Object> confirmOrder(Integer userId, OrderDetailsVo orderDetailsVo) {
        log.info("in confirmOrder()");
        Optional<User> userOptional = userRepo.findById(userId);
        if(userOptional.isEmpty()){
            log.info("Invalid user");
            return ResponseUtils.failureResponse("Invalid User", ReasonCode.INVALID_PARAMETER.value());
        }
        if(userId != orderDetailsVo.getUserId()){
            log.info("Header and body userid dose not match");
            return ResponseUtils.failureResponse("Header and body userid dose not match", ReasonCode.INVALID_PARAMETER.value());
        }
        UserOrders userOrders = new UserOrders();
        userOrders.setUserId(orderDetailsVo.getUserId());
        userOrders.setProductId(orderDetailsVo.getProductId());
        userOrders.setUserAddress(orderDetailsVo.getUserAddress());

        userOrdersRepo.save(userOrders);
        return ResponseUtils.successResponse(userOrders);
    }
}
