package com.nmt.saleapp.service;

import com.nmt.saleapp.model.Cart;

import java.util.Map;

public interface ReceiptService {
    boolean addReceipt(Map<String, Cart> carts);
}
