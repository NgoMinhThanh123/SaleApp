package com.nmt.saleapp.service;

import com.nmt.saleapp.model.Category;
import com.nmt.saleapp.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Page<Category> findAll(Pageable pageable);
    Optional<Category> findById(int id);
    Page<Category> findAllByNameContaining(String keyword, Pageable pageable);
    Category save(Category p);
    boolean deleteCategory(int categoryId);
}
