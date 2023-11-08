package com.nmt.saleapp.repository;

import com.nmt.saleapp.dto.OrderDetailDto;
import com.nmt.saleapp.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query("select new com.nmt.saleapp.dto.OrderDetailDto(count(a.productId.id)) " +
            "from OrderDetail a " +
            "where a.productId.id = :productId")
    OrderDetailDto getNumberOfSales(@Param("productId") Long productId);
}
