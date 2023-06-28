package com.sudip.ecommerce_poc.repository;

import com.sudip.ecommerce_poc.entity.ProductMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductMasterRepo extends JpaRepository<ProductMaster, Integer> {
    Optional<ProductMaster> findByCategory(String category);
}
