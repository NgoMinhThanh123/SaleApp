package com.nmt.saleapp.controller;

import com.nmt.saleapp.dto.CommentDto;
import com.nmt.saleapp.model.Category;
import com.nmt.saleapp.model.Comment;
import com.nmt.saleapp.model.Product;
import com.nmt.saleapp.service.CategoryService;
import com.nmt.saleapp.service.CommentService;
import com.nmt.saleapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService cateService;
    @Autowired
    private CommentService commentService;

    @DeleteMapping("/update_product/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
        this.productService.deleteProduct(id);
    }

    @RequestMapping("/products/")
    public ResponseEntity<List<Product>> list(
            @RequestParam(required = false) String kw,
            @RequestParam(required = false) Integer cateId
    ) {
        List<Product> list;

        if (cateId != null) {
            list = productService.findByCategoryId_Id(cateId);
        } else if (kw != null && !kw.isEmpty()) {
            list = productService.findByNameContaining(kw);
        } else {
            list = productService.findAll();
        }

        if (list.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(path = "/products/{productId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> details(@PathVariable(value = "productId") int id) {
        Optional<Product> productOptional = this.productService.findById(id);
        if (productOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product product = productOptional.get();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping(path = "/products/", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] file) {
        Product p = new Product();
        p.setName(params.get("name"));
        p.setDescription(params.get("description"));
        p.setPrice(Long.parseLong(params.get("price")));
        Optional<Category> categoryOptional = this.cateService.findById(Integer.parseInt(params.get("categoryId")));
        Category category = categoryOptional.get();
        p.setCategoryId(category);
        if (file.length > 0)
            p.setFile(file[0]);
        this.productService.save(p);
    }

    @GetMapping("/products/{productId}/comments/")
    public ResponseEntity<List<CommentDto>> listComments(@PathVariable(value = "productId") int id) {
        return new ResponseEntity<>(this.commentService.getCommentByProductId(id), HttpStatus.OK);
    }

    @PostMapping(path="/comments/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment c = this.commentService.save(comment);

        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }
}
