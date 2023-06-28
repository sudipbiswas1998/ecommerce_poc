package com.sudip.ecommerce_poc.controller;

import com.sudip.ecommerce_poc.service.UserOrdersService;
import com.sudip.ecommerce_poc.util.BLUtil;
import com.sudip.ecommerce_poc.vo.OrderDetailsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/order")
public class UserOrdersController {

    @Autowired
    private UserOrdersService userOrdersService;

    @GetMapping("/get_by_id")
    public ResponseEntity<Map<String, Object>> getProductById(@RequestParam("order_id") Integer orderId, @RequestHeader("user_id") Integer userId){
        log.info("in getProductById controller");
        Map<String, Object> response = userOrdersService.getProductById(orderId, userId);
        return sendResponse(response);
    }

    @PostMapping("/confirm")
    public ResponseEntity<Map<String, Object>> confirmOrder(@RequestHeader("user_id") Integer userId, @RequestBody OrderDetailsVo orderDetailsVo){
        log.info("in confirmOrder controller");
        Map<String, Object> response = userOrdersService.confirmOrder(userId, orderDetailsVo);
        return sendResponse(response);
    }
    private ResponseEntity<Map<String, Object>> sendResponse(Map<String, Object> responseMap) {
        if (!BLUtil.validateResponseStatus(responseMap))
            return ResponseEntity.badRequest().body(responseMap);
        return ResponseEntity.ok(responseMap);
    }
}
