package com.nmt.saleapp.service.Impl;

import com.nmt.saleapp.dto.OrderDetailDto;
import com.nmt.saleapp.repository.OrderDetailRepository;
import com.nmt.saleapp.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetailDto getNumberOfSales(Long productId) {
        return this.orderDetailRepository.getNumberOfSales(productId);
    }
}
