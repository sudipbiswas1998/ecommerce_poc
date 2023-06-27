package com.sudip.ecommerce_poc.service;

import com.sudip.ecommerce_poc.entity.User;
import com.sudip.ecommerce_poc.repository.UserRepo;
import com.sudip.ecommerce_poc.util.ResponseUtils;
import com.sudip.ecommerce_poc.vo.UserVo;
import com.sudip.ecommerce_poc.vo.response.ReasonCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public Map<String, Object> saveUser(UserVo userVo) {
        log.info("in saveUser()");
        Optional<User> userOptional = userRepo.findByMobileNo(userVo.getMobileNo());
        if(userOptional.isEmpty()){
            User user = new User();
            user.setEmail(userVo.getEmail());
            user.setMobileNo(userVo.getMobileNo());
            user.setFirstName(userVo.getFirstName());
            user.setLastName(userVo.getLastName());
            userRepo.save(user);
            return ResponseUtils.successResponse(user);
        }else {
            log.info("User already present");
            return ResponseUtils.failureResponse("Duplicate registration", ReasonCode.ALREADY_PRESENT.value());
        }
    }

    public Map<String, Object> getUser(Integer userId) {
        log.info("in getUser()");
        Optional<User> userOptional = userRepo.findById(userId);
        if(userOptional.isPresent()){
            return ResponseUtils.successResponse(userOptional.get());
        }else {
            log.info("User not present with userId {}", userId);
            return ResponseUtils.failureResponse("User not present", ReasonCode.NON_EXISTENT_CUSTOMER_ID.value());
        }
    }
}
