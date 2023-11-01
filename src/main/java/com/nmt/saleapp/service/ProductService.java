package com.nmt.saleapp.service;

import com.nmt.saleapp.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Page<Product> findAll(Pageable pageable);
    Optional<Product> findById(int id);
    Product getProductById(int id);
    Page<Product> findAllByNameContaining(String keyword, Pageable pageable);
    Page<Product> findByCategoryId_Id(int categoryId, Pageable pageable);
    List<Product> findByCategoryId_Id(int categoryId);
    List<Product> findByNameContaining(String keyword);
    Product save(Product p);
    boolean deleteProduct(int id);
}
