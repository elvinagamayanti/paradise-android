/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.paradiseppk.controller;

import com.example.paradiseppk.dto.MemberDto;
import com.example.paradiseppk.service.JasaService;
import com.example.paradiseppk.service.MemberService;
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
public class MemberController {
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private JasaService jasaService;

    @Operation(summary = "Melihat semua member yang ada di dalam sistem. Hanya bisa diakses admin.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "semua data member", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MemberDto.class)))
            })
        }
    )
    @GetMapping("/member")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> getAll(Principal principal) {
        List<MemberDto> members = memberService.getMembers();
        return ResponseEntity.ok().body(members);
    }
    
    @Operation(summary = "Melihat member tertentu. Hanya bisa diakses admin.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "data member berdasarkan id", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MemberDto.class)))
            })
        }
    )
    @GetMapping("/member/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> getMember(@PathVariable Long id, Principal principal) {
        MemberDto member = memberService.getMember(id);
        return ResponseEntity.ok().body(member);
    }


    @Operation(summary = "Membuat member. Bisa diakses oleh semua user.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "201", description = "berhasil membuat member", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = MemberDto.class))
            })
        }
    )
    @PostMapping("/member")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<?> create(Principal principal, @RequestBody @Valid MemberDto memberDto) {
        MemberDto result = memberService.createMember(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(summary = "Mengubah isi data member yang sudah ada. Hanya dapat mengubah nama, angkatan, dan divisi.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "berhasil mengubah member", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = MemberDto.class))
            })
        }
    )
    @PutMapping("/member/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> edit(@Parameter(description = "id dari member yang ingin diubah") @PathVariable("id") Long memberId, @RequestBody @Valid MemberDto request) {
        MemberDto memberDto = memberService.getMember(memberId);
        memberDto.setNama(request.getNama());
        memberDto.setAngkatan(request.getAngkatan());
        memberDto.setDivisi(request.getDivisi());

        memberDto = memberService.updateMember(memberDto);
        return ResponseEntity.ok().body(memberDto);
    }

    @Operation(summary = "Menghapus member dengan id tertentu.")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "berhasil menghapus member", content = @Content)
        }
    )
    @DeleteMapping("/member/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> destroy(@Parameter(description = "id dari member yang ingin dihapus") @PathVariable("id") Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }
}
