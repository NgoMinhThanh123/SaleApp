package com.nmt.saleapp.service.Impl;

import com.nmt.saleapp.dto.CommentAndRating;
import com.nmt.saleapp.dto.CommentDto;
import com.nmt.saleapp.model.Comment;
import com.nmt.saleapp.model.Preview;
import com.nmt.saleapp.model.Product;
import com.nmt.saleapp.model.User;
import com.nmt.saleapp.repository.CommentRepository;
import com.nmt.saleapp.repository.PreviewRepository;
import com.nmt.saleapp.repository.ProductRepository;
import com.nmt.saleapp.repository.UserRepository;
import com.nmt.saleapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PreviewRepository previewRepository;

    @Override
    public List<Comment> findAll() {
        return this.commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(int id) {
        return this.commentRepository.findById(id);
    }

    @Override
    public CommentAndRating save(CommentAndRating c) {
        Comment comment = new Comment();
        comment.setContent(c.getContent());
        comment.setCreatedDate(new Date());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userRepository.getUserByUsername(authentication.getName());
        comment.setUser(u);
        Product product = this.productRepository.getProductById(c.getProductId());
        comment.setProduct(product);
        this.commentRepository.save(comment);

        Preview preview = new Preview();
        preview.setDateCreated(new Date());
        preview.setPreview(c.getPreview());
        preview.setUserId(u);
        preview.setCommentId(comment);

        this.previewRepository.save(preview);

        return c;
    }

    @Override
    public boolean deleteComment(int id) {
        this.commentRepository.deleteById(id);
        return true;
    }

    @Override
    public List<CommentDto> getCommentByProductId(int productId) {
        return this.commentRepository.getCommentByProductId(productId);
    }
}
