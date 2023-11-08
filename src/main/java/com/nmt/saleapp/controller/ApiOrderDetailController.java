package com.nmt.saleapp.controller;


import com.nmt.saleapp.dto.OrderDetailDto;
import com.nmt.saleapp.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiOrderDetailController {
    @Autowired
    private OrderDetailService oderDetailService;

    @GetMapping("/oder-detail/{productId}/")
    public ResponseEntity<OrderDetailDto> getNumberOfSales(@PathVariable("productId") Long productId){

        return new ResponseEntity<>(this.oderDetailService.getNumberOfSales(productId), HttpStatus.OK);
    }
}
