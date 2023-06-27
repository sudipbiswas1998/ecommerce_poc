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
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "category")
    private String category;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_title")
    private String productTitle;
    @Column(name = "product_image")
    private String productImage;
    @Column(name = "product_price")
    private String productPrice;
    @Column(name = "created_time")
    @CreationTimestamp
    private LocalDateTime createdTime;
    @Column(name = "updated_time")
    @UpdateTimestamp
    private LocalDateTime updatedTime;

}
