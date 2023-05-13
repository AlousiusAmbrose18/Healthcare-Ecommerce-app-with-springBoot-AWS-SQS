package com.tbd.health.dto;

import com.tbd.health.entity.Customer;
import com.tbd.health.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private Customer customer;

    private Product product;

    private Integer quantity;

    private String status;


}
