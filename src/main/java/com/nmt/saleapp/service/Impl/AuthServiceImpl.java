package com.nmt.saleapp.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nmt.saleapp.dto.JwtResponse;
import com.nmt.saleapp.exception.SaleAppApiException;
import com.nmt.saleapp.model.User;
import com.nmt.saleapp.repository.UserRepository;
import com.nmt.saleapp.request.RegisterRequest;
import com.nmt.saleapp.security.JwtTokenProvider;
import com.nmt.saleapp.service.AuthService;
import com.nmt.saleapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private BCryptPasswordEncoder passEncoder;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public JwtResponse login(UserDetails userDetails) {
        JwtResponse jwtResponse = tokenProvider.generateToken(userDetails);
        return jwtResponse;
    }

    @Override
    public User userRegister(RegisterRequest registerReq, MultipartFile avatar) {
        if (this.userService.getUserByUsername(registerReq.getUsername()) != null) {
            throw new SaleAppApiException(HttpStatus.BAD_REQUEST, "Username is already exist");
        }
        if (this.userService.getUserByEmail(registerReq.getEmail()) != null) {
            throw new SaleAppApiException(HttpStatus.BAD_REQUEST, "Email is already exist");
        }
        User u = new User();
        u.setFirstName(registerReq.getFirstName());
        u.setLastName(registerReq.getLastName());
        u.setEmail(registerReq.getEmail());
        u.setPhone(registerReq.getPhone());
        u.setUsername(registerReq.getUsername());
        u.setPassword(this.passEncoder.encode(registerReq.getPassword()));
        u.setUserRole("ROLE_USER");
        if (!avatar.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(avatar.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.userRepo.save(u);
        return u;
    }
}
