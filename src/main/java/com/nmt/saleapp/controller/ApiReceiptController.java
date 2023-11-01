package com.nmt.saleapp.controller;

import com.nmt.saleapp.model.Cart;
import com.nmt.saleapp.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiReceiptController {
    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/pay/")
    @ResponseStatus(HttpStatus.OK)
    public void pay(@RequestBody Map<String, Cart> carts) {
        this.receiptService.addReceipt(carts);
    }
}
