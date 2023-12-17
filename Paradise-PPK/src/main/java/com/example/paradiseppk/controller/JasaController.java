/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.paradiseppk.controller;

import com.example.paradiseppk.dto.JasaDto;
import com.example.paradiseppk.service.JasaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pinaa
 */
@RestController
@RequestMapping
public class JasaController {
    
    @Autowired
    private JasaService jasaService;

    @Operation(summary = "Melihat semua daftar permohonan jasa yang ada di dalam sistem. Bisa diakses semua user.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "semua data jasa", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = JasaDto.class)))
            })
        }
    )
    @GetMapping("/jasa")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> getAll(Principal principal) {
        List<JasaDto> jasas = jasaService.getJasas();
        return ResponseEntity.ok().body(jasas);
    }
    
    @Operation(summary = "Melihat daftar permohonan jasa tertentu. Bisa diakses semua user.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "data jasa berdasarkan id", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = JasaDto.class)))
            })
        }
    )
    @GetMapping("/jasa/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> getJasa(@PathVariable Long id, Principal principal) {
        JasaDto jasa = jasaService.getJasa(id);
        return ResponseEntity.ok().body(jasa);
    }


    @Operation(summary = "Membuat permohonan jasa. Bisa diakses oleh semua user.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "201", description = "berhasil membuat permohonan jasa", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = JasaDto.class))
            })
        }
    )
    @PostMapping("/jasa")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<?> create(Principal principal, @RequestBody @Valid JasaDto jasaDto) {
        JasaDto result = jasaService.createJasa(jasaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(summary = "Mengubah status permohonan jasa yang sudah ada.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "berhasil mengubah status permohonan jasa", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = JasaDto.class))
            })
        }
    )
    @PatchMapping("/jasa/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> edit(@Parameter(description = "id dari jasa yang ingin diubah") @PathVariable("id") Long jasaId, @RequestBody @Valid JasaDto request) {
        JasaDto jasaDto = jasaService.getJasa(jasaId);
        jasaDto.setStatus(request.getStatus());

        jasaDto = jasaService.updateJasa(jasaDto);
        return ResponseEntity.ok().body(jasaDto);
    }

    @Operation(summary = "Menghapus permohonan jasa dengan id tertentu.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "berhasil menghapus data jasa", content = @Content)
        }
    )
    @DeleteMapping("/jasa/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> destroy(@Parameter(description = "id dari jasa yang ingin dihapus") @PathVariable("id") Long jasaId) {
        jasaService.deleteJasa(jasaId);
        return ResponseEntity.ok().build();
    }
}
