package com.nmt.saleapp.service;

import com.nmt.saleapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<User> findAll();
    Page<User> findAll(Pageable pageable);
    Optional<User> findById(int id);
    User findByUsername(String username);
    User save(User u);
    boolean deleteUser(int id);
    User getUserByUsername(String username);
    Page<User> findAllByUsernameContaining(String keyword, Pageable pageable);

    User getUserByEmail(String email);
}
