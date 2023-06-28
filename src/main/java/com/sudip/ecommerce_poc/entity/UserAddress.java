package com.sudip.ecommerce_poc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_address")
public class UserAddress {
    @EmbeddedId
    private UserAddressIds userAddressIds;
    @Column(name = "created_time")
    @CreationTimestamp
    private LocalDateTime createdTime;
    @Column(name = "updated_time")
    @UpdateTimestamp
    private LocalDateTime updatedTime;
}
