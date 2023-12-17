/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.paradiseppk.controller;

import com.example.paradiseppk.dto.UserDto;
import com.example.paradiseppk.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pinaa
 */
@RestController
public class UserManagementController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Menampilkan seluruh user yang terdaftar dalam sistem.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "seluruh data user", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))
            })
        }
    )
    @GetMapping("/user")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @Operation(summary = "Menghapus user tertentu.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "berhasil menghapus user", content = @Content)
        }
    )
    @DeleteMapping("/user/{userId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> deleteAccount(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
