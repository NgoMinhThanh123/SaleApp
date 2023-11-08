package com.nmt.saleapp.controller;

import com.nmt.saleapp.dto.CommentDto;
import com.nmt.saleapp.dto.PreviewDto;
import com.nmt.saleapp.dto.PreviewProduct;
import com.nmt.saleapp.model.Comment;
import com.nmt.saleapp.model.Preview;
import com.nmt.saleapp.service.PreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiPreviewController {
    @Autowired
    private PreviewService previewService;

    @GetMapping("/products/{productId}/previews/")
    public ResponseEntity<List<PreviewDto>> listPreviews(@PathVariable(value = "productId") int id) {
        return new ResponseEntity<>(this.previewService.getPreviewByProductId(id), HttpStatus.OK);
    }

    @GetMapping("/previews/{productId}/")
    public ResponseEntity<PreviewProduct> getPreviewOfProduct(@PathVariable(value = "productId") int id) {
        PreviewProduct product = this.previewService.getPreviewOfProduct(id);
        if (product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
