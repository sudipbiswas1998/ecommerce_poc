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
@Table(name = "user_orders")
public class UserOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "address")
    private String userAddress;
    @Column(name = "created_time")
    @CreationTimestamp
    private LocalDateTime createdTime;
    @Column(name = "updated_time")
    @UpdateTimestamp
    private LocalDateTime updatedTime;
}
