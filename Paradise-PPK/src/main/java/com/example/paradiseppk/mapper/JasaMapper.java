package com.example.paradiseppk.mapper;

import com.example.paradiseppk.dto.JasaDto;
import com.example.paradiseppk.entity.Jasa;

public class JasaMapper {
    public static Jasa mapToJasa(JasaDto jasaDto){
        return Jasa.builder()
                .id(jasaDto.getId())
                .namaAcara(jasaDto.getNamaAcara())
                .tanggalAcara(jasaDto.getTanggalAcara())
                .jenisJasa(jasaDto.getJenisJasa())
                .jenisTari(jasaDto.getJenisTari())
                .jumlahPenari(jasaDto.getJumlahPenari())
                .status(jasaDto.getStatus())
                .build();
    }
    public static JasaDto mapToJasaDto(Jasa jasa){
        return JasaDto.builder()
                .id(jasa.getId())
                .namaAcara(jasa.getNamaAcara())
                .tanggalAcara(jasa.getTanggalAcara())
                .jenisJasa(jasa.getJenisJasa())
                .jenisTari(jasa.getJenisTari())
                .jumlahPenari(jasa.getJumlahPenari())
                .status(jasa.getStatus())
                .build();
    }
}
