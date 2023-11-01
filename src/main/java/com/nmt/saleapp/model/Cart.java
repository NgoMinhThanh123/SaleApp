package com.nmt.saleapp.model;

import lombok.Data;

@Data
public class Cart {
    private Long id;
    private String name;
    private int quantity;
    private Long unitPrice;
}
