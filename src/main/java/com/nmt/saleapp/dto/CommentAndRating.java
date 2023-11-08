package com.nmt.saleapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentAndRating {
    private String content;
    private Integer preview;
    private Integer productId;
}
