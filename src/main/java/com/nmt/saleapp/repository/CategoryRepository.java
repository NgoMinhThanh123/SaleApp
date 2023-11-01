package com.nmt.saleapp.repository;

import com.nmt.saleapp.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findById(int id);
    Page<Category> findByNameContaining(String keyword, Pageable pageable);
    Category save(Category u);
    void deleteById(int id);
}
