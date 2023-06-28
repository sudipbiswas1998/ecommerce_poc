package com.sudip.ecommerce_poc.service;

import com.sudip.ecommerce_poc.entity.UserAddress;
import com.sudip.ecommerce_poc.entity.UserAddressIds;
import com.sudip.ecommerce_poc.repository.UserAddressRepo;
import com.sudip.ecommerce_poc.util.ResponseUtils;
import com.sudip.ecommerce_poc.vo.response.ReasonCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserAddressService {
    @Autowired
    private UserAddressRepo userAddressRepo;

    public Map<String, Object> getAddress(Integer userId) {
        log.info("in getAddress()");
        List<UserAddress> userAddresses = userAddressRepo.findAllByUserId(userId);
        List<String> address = new ArrayList<>();
        for(UserAddress userAddress: userAddresses){
            address.add(userAddress.getUserAddressIds().getAddress());
        }
        return ResponseUtils.successResponse(address);
    }

    public Map<String, Object> addAddress(Integer userId, Map<String, Object> body) {
        log.info(" in addAddress()");
        UserAddressIds userAddressIds = new UserAddressIds();
        userAddressIds.setAddress(String.valueOf(body.get("address")));
        userAddressIds.setUserId(userId);
        Optional<UserAddress> userAddressOptional = userAddressRepo.findUserAddressByUserAddressIds(userAddressIds);
        if (userAddressOptional.isPresent()){
            log.info("Address already present");
            return ResponseUtils.failureResponse("Address already present for current user", ReasonCode.ALREADY_PRESENT.value());
        }else {
            UserAddress userAddress = new UserAddress();
            userAddress.setUserAddressIds(userAddressIds);
            userAddressRepo.save(userAddress);
            return ResponseUtils.successResponse(userAddress);
        }
    }
}
