package com.nmt.saleapp.repository;

import com.nmt.saleapp.dto.PreviewDto;
import com.nmt.saleapp.dto.PreviewProduct;
import com.nmt.saleapp.model.Preview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreviewRepository extends JpaRepository<Preview, Integer> {
    Optional<Preview> findById(int id);

    Preview save(Preview u);

    void deleteById(int id);

    @Query("select new com.nmt.saleapp.dto.PreviewDto(pr.id, pr.dateCreated, pr.preview, pr.userId, pr.commentId ) " +
            "from Preview pr " +
            "join Comment c on c.id = pr.commentId.id " +
            "join Product p on p.id = c.product.id " +
            "where p.id = :productId")
    List<PreviewDto> getPreviewByProductId(@Param("productId") int productId);

    @Query("select new com.nmt.saleapp.dto.PreviewProduct(COALESCE(round(avg(a.preview), 1), 0), count(a.preview) ) from Preview a " +
            "join Comment c on c.id = a.commentId.id " +
            "join Product p on p.id = c.product.id " +
            "where p.id = :productId")
    PreviewProduct getPreviewOfProduct(@Param("productId") int productId);
}
