package com.sudip.ecommerce_poc.repository;

import com.sudip.ecommerce_poc.entity.ProductMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductMasterRepo extends JpaRepository<ProductMaster, Integer> {
    Optional<ProductMaster> findByCategory(String category);
}
