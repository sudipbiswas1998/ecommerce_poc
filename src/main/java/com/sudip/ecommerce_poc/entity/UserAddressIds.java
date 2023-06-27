package com.sudip.ecommerce_poc.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserAddressIds implements Serializable {
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "address")
    private String address;
}
