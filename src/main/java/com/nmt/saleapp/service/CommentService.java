package com.nmt.saleapp.service;

import com.nmt.saleapp.dto.CommentAndRating;
import com.nmt.saleapp.dto.CommentDto;
import com.nmt.saleapp.model.Category;
import com.nmt.saleapp.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> findAll();
    Optional<Comment> findById(int id);
    CommentAndRating save(CommentAndRating c);
    boolean deleteComment(int id);
    List<CommentDto> getCommentByProductId(@Param("productId") int productId);
}
