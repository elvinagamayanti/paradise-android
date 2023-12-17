package com.example.paradiseppk.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nim;
    
    @Column(nullable = false)
    private String nama;
    
    @Column(nullable = false)
    private String angkatan;
    
    @Column(nullable = false)
    private EDivisi divisi;
    
    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Jasa> jasas;
}
