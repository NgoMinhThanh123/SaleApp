package com.nmt.saleapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nmt.saleapp.dto.JwtResponse;
import com.nmt.saleapp.model.User;
import com.nmt.saleapp.request.LoginRequest;
import com.nmt.saleapp.request.RegisterRequest;
import com.nmt.saleapp.service.AuthService;
import com.nmt.saleapp.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Tag(name = "User Controller")
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiAuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthService authService;

    private void authenticate(final String username, final String password) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @PostMapping("/login/")
    public ResponseEntity<?> login(@RequestBody @Valid final LoginRequest loginRequest) {
        try {
            authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Username or password is invalid!");
        }

        final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
        JwtResponse jwtResponse = authService.login(userDetails);
        return ResponseEntity.ok().body(jwtResponse);
    }

    @PostMapping(path = "/register/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> addUser( @RequestParam("registerRequest") final String registerRequest,
                                         @RequestPart("avatar") final MultipartFile avatar){
            ObjectMapper objectMapper = new ObjectMapper();
        try {
            RegisterRequest req = objectMapper.readValue(registerRequest, RegisterRequest.class);
            User user = this.authService.userRegister(req, avatar);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping(path = "/current-user/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> details(Principal user) {
        User u = this.userService.findByUsername(user.getName());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
}
