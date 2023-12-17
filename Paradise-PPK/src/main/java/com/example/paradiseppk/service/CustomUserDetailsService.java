package com.example.paradiseppk.service;

import com.example.paradiseppk.dto.UserDto;
import com.example.paradiseppk.entity.User;
import com.example.paradiseppk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDto loadUserByUsername(String email) throws
            UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " +
                    email);
        }
        return UserDto.build(user);
    }
}
