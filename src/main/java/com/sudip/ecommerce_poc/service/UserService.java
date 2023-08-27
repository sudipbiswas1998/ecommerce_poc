package com.sudip.ecommerce_poc.service;

import com.sudip.ecommerce_poc.entity.User;
import com.sudip.ecommerce_poc.entity.UserAddress;
import com.sudip.ecommerce_poc.entity.UserAddressIds;
import com.sudip.ecommerce_poc.repository.UserAddressRepo;
import com.sudip.ecommerce_poc.repository.UserRepo;
import com.sudip.ecommerce_poc.util.EncryptDecryptUtil;
import com.sudip.ecommerce_poc.util.ResponseUtils;
import com.sudip.ecommerce_poc.vo.UserVo;
import com.sudip.ecommerce_poc.vo.response.ReasonCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserAddressRepo userAddressRepo;
    @Autowired
    private EncryptDecryptUtil encryptDecryptUtil;

    public Map<String, Object> saveUser(UserVo userVo) {
        log.info("in saveUser()");
        if(userVo.getMobileNo().isEmpty() || userVo.getAddress().isEmpty() ||
                userVo.getEmail().isEmpty() || userVo.getFirstName().isEmpty() || userVo.getLastName().isEmpty()){
            return ResponseUtils.failureResponse("Details are mandatory", ReasonCode.INVALID_PARAMETER.value());
        }
        Optional<User> userOptional = userRepo.findByMobileNo(userVo.getMobileNo());
        if(userOptional.isEmpty()){
            User user = new User();
            user.setEmail(userVo.getEmail());
            user.setMobileNo(userVo.getMobileNo());
            user.setFirstName(userVo.getFirstName());
            user.setLastName(userVo.getLastName());
            user.setPassword(encryptDecryptUtil.encryptPII(userVo.getPassword()));
            userRepo.save(user);

            UserAddressIds userAddressIds = new UserAddressIds();
            userAddressIds.setUserId(user.getId());
            userAddressIds.setAddress(userVo.getAddress());
            UserAddress userAddress = new UserAddress();
            userAddress.setUserAddressIds(userAddressIds);
            userAddressRepo.save(userAddress);

            return ResponseUtils.successResponse(user);
        }else {
            log.error("User already present");
            return ResponseUtils.failureResponse("Duplicate registration", ReasonCode.ALREADY_PRESENT.value());
        }
    }

    public Map<String, Object> getUser(Integer userId) {
        log.info("in getUser()");
        Optional<User> userOptional = userRepo.findById(userId);
        if(userOptional.isPresent()){
            return ResponseUtils.successResponse(userOptional.get());
        }else {
            log.error("User not present with userId {}", userId);
            return ResponseUtils.failureResponse("User not present", ReasonCode.NON_EXISTENT_CUSTOMER_ID.value());
        }
    }

    public Map<String, Object> login(Map<String, Object> body) {
        String userMobile = String.valueOf(body.get("user"));
        String password = String.valueOf(body.get("password"));
        Optional<User> userOptional = userRepo.findByMobileNoAndPassword(userMobile, encryptDecryptUtil.encryptPII(password));
        if (userOptional.isPresent()){
            log.info("user present");
            return ResponseUtils.successResponse(userOptional.get());
        }else {
            log.error("User not present with mobile no {}", userMobile);
            return ResponseUtils.failureResponse("User not present", ReasonCode.NON_EXISTENT_CUSTOMER_ID.value());
        }
    }
}
