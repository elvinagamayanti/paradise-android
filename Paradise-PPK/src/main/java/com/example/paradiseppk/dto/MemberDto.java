/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.paradiseppk.dto;

import com.example.paradiseppk.entity.EDivisi;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author pinaa
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    
    @NotEmpty(message = "Nim wajib diisi.")
    private String nim;
    
    @NotEmpty(message = "Nama wajib diisi.")
    private String nama;
    
    @NotEmpty(message = "Angkatan wajib diisi.")
    private String angkatan;
    
    @NotNull(message = "Divisi wajib diisi.")
    private EDivisi divisi;
}
