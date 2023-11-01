package com.nmt.saleapp.service;

import com.nmt.saleapp.dto.JwtResponse;
import com.nmt.saleapp.model.User;
import com.nmt.saleapp.request.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

public interface AuthService {
    JwtResponse login(UserDetails userDetails);

    User userRegister(RegisterRequest registerReq, MultipartFile avatar);
}
