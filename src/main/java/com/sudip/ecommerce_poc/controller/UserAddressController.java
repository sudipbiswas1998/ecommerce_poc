package com.sudip.ecommerce_poc.controller;

import com.sudip.ecommerce_poc.service.UserAddressService;
import com.sudip.ecommerce_poc.util.BLUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/address")
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;

    @GetMapping("/get_address")
    public ResponseEntity<Map<String, Object>> getUserAddress(@RequestHeader("user_id") Integer userId){
        log.info("in getUserAddress controller");
        Map<String, Object> response = userAddressService.getAddress(userId);
        return sendResponse(response);
    }

    @PostMapping("/add_address")
    public ResponseEntity<Map<String, Object>> addAddress(@RequestHeader("user_id") Integer userId, HttpEntity<Map<String, Object>> httpEntity){
        log.info("in addAddress controller");
        Map<String, Object> response = userAddressService.addAddress(userId, httpEntity.getBody());
        return sendResponse(response);
    }

    private ResponseEntity<Map<String, Object>> sendResponse(Map<String, Object> responseMap) {
        if (!BLUtil.validateResponseStatus(responseMap))
            return ResponseEntity.badRequest().body(responseMap);
        return ResponseEntity.ok(responseMap);
    }
}
