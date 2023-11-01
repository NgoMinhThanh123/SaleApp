package com.nmt.saleapp.controller;

import com.nmt.saleapp.model.Category;
import com.nmt.saleapp.model.Product;
import com.nmt.saleapp.service.CategoryService;
import com.nmt.saleapp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/product")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "kw", required = false) String keyword) {
        int pageSize = 12;
        Page<Product> productPage;

        if (keyword != null && !keyword.isEmpty()) {
            productPage = this.productService.findAllByNameContaining(keyword, PageRequest.of(page, pageSize));
        } else {
            productPage = this.productService.findAll(PageRequest.of(page, pageSize));
        }

        model.addAttribute("productPage", productPage);
        model.addAttribute("keyword", keyword);

        return "product";
    }

    @GetMapping("/add_product")
    public String addList(Model model) {
        model.addAttribute("add_product", new Product());
        model.addAttribute("category", this.categoryService.findAll());

        return "add_product";
    }

    @GetMapping("/update_product/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("update_product", this.productService.getProductById(id));
        model.addAttribute("category", this.categoryService.findAll());

        return "update_product";
    }

    @PostMapping("/add_product")
    public String add(@ModelAttribute(value = "add_product") @Valid Product v,
                      BindingResult rs) {
        if(!rs.hasErrors()){
            this.productService.save(v);
            return "redirect:/product";

        }
        return "add_product";
    }
    @PostMapping("/update_product")
    public String update(@ModelAttribute(value = "update_product") @Valid Product u,
                         BindingResult rs) {
        if(!rs.hasErrors()){
            this.productService.save(u);
            return "redirect:/product";

        }
        return "update_product";
    }
}
