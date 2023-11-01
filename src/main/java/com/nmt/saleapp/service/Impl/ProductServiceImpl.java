package com.nmt.saleapp.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nmt.saleapp.model.Product;
import com.nmt.saleapp.repository.ProductRepository;
import com.nmt.saleapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(int id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Product getProductById(int id) {
        return this.productRepository.getProductById(id);
    }

    @Override
    public Page<Product> findAllByNameContaining(String keyword, Pageable pageable) {
        return this.productRepository.findByNameContaining(keyword, pageable);
    }

    @Override
    public Page<Product> findByCategoryId_Id(int categoryId, Pageable pageable) {
        return this.productRepository.findByCategoryId_Id(categoryId, pageable);
    }

    @Override
    public List<Product> findByCategoryId_Id(int categoryId) {
        return this.productRepository.findByCategoryId_Id(categoryId);
    }

    @Override
    public List<Product> findByNameContaining(String keyword) {
        return this.productRepository.findByNameContaining(keyword);
    }

    @Override
    public Product save(Product p) {
        if (!p.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(p.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                p.setImage(res.get("secure_url").toString());

            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.productRepository.save(p);
    }

    @Override
    public boolean deleteProduct(int id) {
        this.productRepository.deleteById(id);
        return true;
    }
}
