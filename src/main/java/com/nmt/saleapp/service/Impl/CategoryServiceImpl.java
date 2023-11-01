package com.nmt.saleapp.service.Impl;

import com.nmt.saleapp.model.Category;
import com.nmt.saleapp.model.Product;
import com.nmt.saleapp.repository.CategoryRepository;
import com.nmt.saleapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return this.categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findById(int id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public Page<Category> findAllByNameContaining(String keyword, Pageable pageable) {
        return this.categoryRepository.findByNameContaining(keyword, pageable);
    }

    @Override
    public Category save(Category p) {
        return this.categoryRepository.save(p);
    }

    @Override
    public boolean deleteCategory(int categoryId) {
        this.categoryRepository.deleteById(categoryId);
        return true;
    }
}
