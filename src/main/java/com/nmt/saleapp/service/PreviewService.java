package com.nmt.saleapp.service;

import com.nmt.saleapp.dto.PreviewDto;
import com.nmt.saleapp.dto.PreviewProduct;
import com.nmt.saleapp.model.Preview;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PreviewService {
    List<Preview> findAll();
    Optional<Preview> findById(int id);
    Preview save(Preview c);
    boolean deletePreview(int id);
    List<PreviewDto> getPreviewByProductId(int productId);
    PreviewProduct getPreviewOfProduct(int productId);
}
