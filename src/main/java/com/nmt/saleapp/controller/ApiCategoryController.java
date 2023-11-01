package com.nmt.saleapp.controller;

import com.nmt.saleapp.model.Category;
import com.nmt.saleapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiCategoryController {
    @Autowired
    private CategoryService categoryService;

    @DeleteMapping("/update_category/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        this.categoryService.deleteCategory(id);
    }

    @GetMapping("/categories/")
    @CrossOrigin
    public ResponseEntity<List<Category>> list() {
        return new ResponseEntity<List<Category>>(this.categoryService.findAll(), HttpStatus.OK);
    }
}
