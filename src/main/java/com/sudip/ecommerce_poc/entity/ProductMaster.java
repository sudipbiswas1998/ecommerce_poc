package com.sudip.ecommerce_poc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_master")
public class ProductMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "category")
    private String category;
    @Column(name = "active_status")
    private Boolean activeStatus;
    @Column(name = "created_time")
    @CreationTimestamp
    private LocalDateTime createdTime;
    @Column(name = "updated_time")
    @UpdateTimestamp
    private LocalDateTime updatedTime;
}
