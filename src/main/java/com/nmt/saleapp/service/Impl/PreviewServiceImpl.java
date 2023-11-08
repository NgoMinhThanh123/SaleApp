package com.nmt.saleapp.service.Impl;

import com.nmt.saleapp.dto.PreviewDto;
import com.nmt.saleapp.dto.PreviewProduct;
import com.nmt.saleapp.model.Preview;
import com.nmt.saleapp.model.User;
import com.nmt.saleapp.repository.PreviewRepository;
import com.nmt.saleapp.repository.UserRepository;
import com.nmt.saleapp.service.PreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PreviewServiceImpl implements PreviewService {
    @Autowired
    private PreviewRepository previewRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Preview> findAll() {
        return this.previewRepository.findAll();
    }

    @Override
    public Optional<Preview> findById(int id) {
        return this.previewRepository.findById(id);
    }

    @Override
    public Preview save(Preview c) {
        c.setDateCreated(new Date());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userRepository.getUserByUsername(authentication.getName());
        c.setUserId(u);
        return this.previewRepository.save(c);
    }

    @Override
    public boolean deletePreview(int id) {
        this.previewRepository.deleteById(id);
        return true;
    }

    @Override
    public List<PreviewDto> getPreviewByProductId(int productId) {
        return this.previewRepository.getPreviewByProductId(productId);
    }

    @Override
    public PreviewProduct getPreviewOfProduct(int productId) {
        return this.previewRepository.getPreviewOfProduct(productId);
    }
}
