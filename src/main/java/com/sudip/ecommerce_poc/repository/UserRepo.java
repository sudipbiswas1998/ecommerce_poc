package com.sudip.ecommerce_poc.repository;

import com.sudip.ecommerce_poc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByMobileNo(String mobileNo);
}
