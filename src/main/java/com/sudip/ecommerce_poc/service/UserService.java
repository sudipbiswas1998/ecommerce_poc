package com.sudip.ecommerce_poc.service;

import com.sudip.ecommerce_poc.entity.User;
import com.sudip.ecommerce_poc.repository.UserAddressRepo;
import com.sudip.ecommerce_poc.repository.UserRepo;
import com.sudip.ecommerce_poc.util.ResponseUtils;
import com.sudip.ecommerce_poc.vo.UserVo;
import com.sudip.ecommerce_poc.vo.response.ReasonCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

    public Map<String, Object> saveUser(UserVo userVo) {
        log.info("in saveUser()");
        if (userVo.getMobileNo().isEmpty() //|| userVo.getAddress().isEmpty()
                || userVo.getEmail().isEmpty() || userVo.getFirstName().isEmpty() || userVo.getLastName().isEmpty()) {
            return ResponseUtils.failureResponse("Details are mandatory", ReasonCode.INVALID_PARAMETER.value());
        }
        Optional<User> userOptional = userRepo.findByMobileNo(userVo.getMobileNo());
        if (userOptional.isEmpty()) {
            User user = new User();
            user.setEmail(userVo.getEmail());
            user.setMobileNo(userVo.getMobileNo());
            user.setFirstName(userVo.getFirstName());
            user.setLastName(userVo.getLastName());
            user.setPassword(passwordEncoder.encode(userVo.getPassword()));
            userRepo.save(user);

            /*UserAddressIds userAddressIds = new UserAddressIds();
            userAddressIds.setUserId(user.getId());
            userAddressIds.setAddress(userVo.getAddress());
            UserAddress userAddress = new UserAddress();
            userAddress.setUserAddressIds(userAddressIds);
            userAddressRepo.save(userAddress);*/

            return ResponseUtils.successResponse(user);
        } else {
            log.error("User already present");
            return ResponseUtils.failureResponse("Duplicate registration", ReasonCode.ALREADY_PRESENT.value());
        }
    }

    public Map<String, Object> getUser(Integer userId) {
        log.info("in getUser()");
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            return ResponseUtils.successResponse(userOptional.get());
        } else {
            log.error("User not present with userId {}", userId);
            return ResponseUtils.failureResponse("User not present", ReasonCode.NON_EXISTENT_CUSTOMER_ID.value());
        }
    }

    public Map<String, Object> login(String mobileNo, String password) {
        log.info("in login()");
        Optional<User> userOptional = userRepo.findByMobileNoAndPassword(mobileNo, passwordEncoder.encode(password));
        if (userOptional.isPresent()) {
            log.info("user present");
            return ResponseUtils.successResponse(userOptional.get());
        } else {
            log.error("User not present with mobile no {}", mobileNo);
            return ResponseUtils.failureResponse("User not present", ReasonCode.NON_EXISTENT_CUSTOMER_ID.value());
        }
    }
}
