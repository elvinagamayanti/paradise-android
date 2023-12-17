package com.example.paradiseppk.repository;

import com.example.paradiseppk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
    boolean existsByEmail(String email);
}
