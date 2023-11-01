package com.nmt.saleapp.dto;

import com.nmt.saleapp.model.Product;
import com.nmt.saleapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private int id;
    private String content;
    private Date createdDate;
    private User user;
    private Product product;
}
