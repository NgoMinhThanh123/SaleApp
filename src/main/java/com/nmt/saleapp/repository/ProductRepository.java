package com.nmt.saleapp.repository;

import com.nmt.saleapp.model.Category;
import com.nmt.saleapp.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findById(int id);

    @Query("select a from Product a where a.id = :id")
    Product getProductById(@Param("id") int id);
    Page<Product> findByNameContaining(String keyword, Pageable pageable);
    Page<Product> findByCategoryId_Id(int categoryId, Pageable pageable);
    List<Product> findByCategoryId_Id(int categoryId);
    List<Product> findByNameContaining(String keyword);

    Product save(Product u);
    void deleteById(int id);
}
