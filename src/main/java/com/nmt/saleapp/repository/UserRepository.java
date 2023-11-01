package com.nmt.saleapp.repository;

import com.nmt.saleapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(int id);
    User findByUsername(String username);
    User save(User u);
    void deleteById(int id);
    @Query("select a from User a where a.username = :username")
    User getUserByUsername(@Param("username") String username);
    Page<User> findAllByUsernameContaining(String keyword, Pageable pageable);

    @Query("select a from User a where a.email = :email")
    User getUserByEmail(@Param("email") String email);
}
