package com.example.paradiseppk.dto;

import com.example.paradiseppk.entity.EJasa;
import com.example.paradiseppk.entity.EStatus;
import com.example.paradiseppk.entity.ETari;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class JasaDto {
    private Long id;
    
    @NotEmpty(message = "Nama Acara wajib diisi.")
    private String namaAcara;
    
    @NotNull(message = "Tanggal Acara wajib diisi.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date tanggalAcara;
    
    private EJasa jenisJasa;
 
    private ETari jenisTari;
    
    @Min(value = 1, message = "Jumlah penari harus lebih besar dari 0")
    private int jumlahPenari;

    private EStatus status;
}
