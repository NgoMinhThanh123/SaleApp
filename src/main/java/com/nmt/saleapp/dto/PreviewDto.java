package com.nmt.saleapp.dto;

import com.nmt.saleapp.model.Comment;
import com.nmt.saleapp.model.Product;
import com.nmt.saleapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreviewDto {
    private Integer id;
    private Date dateCreated;
    private Integer preview;
    private User user;
    private Comment comment;
}
