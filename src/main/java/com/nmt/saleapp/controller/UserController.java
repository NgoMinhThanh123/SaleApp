package com.nmt.saleapp.controller;

import com.nmt.saleapp.model.User;
import com.nmt.saleapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "kw", required = false) String keyword) {
        int pageSize = 12;
        Page<User> userPage;

        if (keyword != null && !keyword.isEmpty()) {
            userPage = this.userService.findAllByUsernameContaining(keyword, PageRequest.of(page, pageSize));
        } else {
            userPage = this.userService.findAll(PageRequest.of(page, pageSize));
        }

        model.addAttribute("userPage", userPage);
        model.addAttribute("keyword", keyword);

        return "user";
    }

    @GetMapping("/add_user")
    public String addList(Model model) {
        model.addAttribute("add_user", new User());

        return "add_user";
    }

    @GetMapping("/update_user/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("update_user", this.userService.findById(id));

        return "update_user";
    }

    @PostMapping("/add_user")
    public String add(@ModelAttribute(value = "add_user")User u,
                      BindingResult rs) {
        if(!rs.hasErrors()){
            this.userService.save(u);
            return "redirect:/user";

        }
        return "add_user";
    }
    @PostMapping("/update_user")
    public String update(@ModelAttribute(value = "update_user")User u,
                         BindingResult rs) {
        if(!rs.hasErrors()){
            this.userService.save(u);
            return "redirect:/user";

        }
        return "update_user";
    }
}