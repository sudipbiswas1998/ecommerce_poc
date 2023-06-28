package com.sudip.ecommerce_poc.repository;

import com.sudip.ecommerce_poc.entity.UserOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrdersRepo extends JpaRepository<UserOrders, Integer> {
}
