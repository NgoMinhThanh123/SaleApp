package com.nmt.saleapp.service;

import com.nmt.saleapp.dto.OrderDetailDto;

public interface OrderDetailService {
    OrderDetailDto getNumberOfSales(Long productId);
}
