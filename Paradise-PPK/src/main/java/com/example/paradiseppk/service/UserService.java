package com.example.paradiseppk.service;

import com.example.paradiseppk.dto.UserDto;
import java.util.List;

public interface UserService{
    public UserDto createUser(UserDto user);
    public UserDto getUserByEmail(String email);
    public UserDto updateUser(UserDto userDto);
    public boolean deleteUser(Long id);
    public List<UserDto> getAllUsers();
}
