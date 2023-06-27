package com.sudip.ecommerce_poc.controller;

import com.sudip.ecommerce_poc.service.UserService;
import com.sudip.ecommerce_poc.util.BLUtil;
import com.sudip.ecommerce_poc.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveUser(@RequestBody UserVo userVo) {
        log.info("in saveUser controller");
        Map<String, Object> response = userService.saveUser(userVo);
        return sendResponse(response);
    }

    @GetMapping("/get_user")
    public ResponseEntity<Map<String, Object>> getUser(@RequestHeader("userId") Integer userId){
        log.info("in getUser controller");
        Map<String, Object> response = userService.getUser(userId);
        return sendResponse(response);
    }

    private ResponseEntity<Map<String, Object>> sendResponse(Map<String, Object> responseMap) {
        if (!BLUtil.validateResponseStatus(responseMap))
            return ResponseEntity.badRequest().body(responseMap);
        return ResponseEntity.ok(responseMap);
    }

}
