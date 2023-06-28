package com.sudip.ecommerce_poc.repository;

import com.sudip.ecommerce_poc.entity.UserAddress;
import com.sudip.ecommerce_poc.entity.UserAddressIds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAddressRepo extends JpaRepository<UserAddress, UserAddressIds> {
    @Query(nativeQuery = true, value = "SELECT * FROM user_address WHERE user_id=:userId")
    List<UserAddress> findAllByUserId(@Param("userId") Integer userId);

    Optional<UserAddress> findUserAddressByUserAddressIds(UserAddressIds userAddressIds);
}
