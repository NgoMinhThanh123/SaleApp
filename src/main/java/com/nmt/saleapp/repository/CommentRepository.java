package com.nmt.saleapp.repository;

import com.nmt.saleapp.dto.CommentDto;
import com.nmt.saleapp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Optional<Comment> findById(int id);
    Comment save(Comment u);
    void deleteById(int id);

    @Query("select new com.nmt.saleapp.dto.CommentDto(c.id, c.content, c.createdDate, c.user, c.product ) " +
            "from Comment c " +
            "join Product p on p.id = c.product.id " +
            "where p.id = :productId")
    List<CommentDto> getCommentByProductId(@Param("productId") int productId);
}
