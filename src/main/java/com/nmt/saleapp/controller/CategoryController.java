package com.nmt.saleapp.controller;

import com.nmt.saleapp.model.Category;
import com.nmt.saleapp.model.User;
import com.nmt.saleapp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "kw", required = false) String keyword) {
        int pageSize = 12;
        Page<Category> categoryPage;

        if (keyword != null && !keyword.isEmpty()) {
            categoryPage = this.categoryService.findAllByNameContaining(keyword, PageRequest.of(page, pageSize));
        } else {
            categoryPage = this.categoryService.findAll(PageRequest.of(page, pageSize));
        }

        model.addAttribute("categoryPage", categoryPage);
        model.addAttribute("keyword", keyword);

        return "category";
    }

    @GetMapping("/add_category")
    public String addList(Model model) {
        model.addAttribute("add_category", new Category());

        return "add_category";
    }

    @GetMapping("/update_category/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("update_category", this.categoryService.findById(id));

        return "update_category";
    }

    @PostMapping("/add_category")
    public String add(@ModelAttribute(value = "add_category") @Valid Category v,
                      BindingResult rs) {
        if(!rs.hasErrors()){
            this.categoryService.save(v);
            return "redirect:/category";

        }
        return "add_category";
    }
    @PostMapping("/update_category")
    public String update(@ModelAttribute(value = "update_category") @Valid Category u,
                         BindingResult rs) {
        if(!rs.hasErrors()){
            this.categoryService.save(u);
            return "redirect:/category";

        }
        return "update_category";
    }
}
