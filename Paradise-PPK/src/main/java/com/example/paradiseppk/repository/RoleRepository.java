package com.example.paradiseppk.repository;

import com.example.paradiseppk.entity.ERole;
import com.example.paradiseppk.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
