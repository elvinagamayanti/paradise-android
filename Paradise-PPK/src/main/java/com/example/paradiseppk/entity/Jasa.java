package com.example.paradiseppk.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_jasa")
public class Jasa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String namaAcara;
    
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date tanggalAcara;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EJasa jenisJasa;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ETari jenisTari;
    
    @Column(nullable = false)
    private int jumlahPenari;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = true, length = 20)
    private EStatus status = EStatus.MENUNGGU_PERSETUJUAN;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private List<Member> members;
}
