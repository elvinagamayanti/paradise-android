package com.example.paradiseppk.service;

import com.example.paradiseppk.dto.JasaDto;
import com.example.paradiseppk.entity.Jasa;

import java.util.List;

public interface JasaService {
    public JasaDto createJasa(JasaDto jasaDto);
    public List<JasaDto> getJasas();
    public List<JasaDto> searchJasas(String query);
    public JasaDto updateJasa(JasaDto jasaDto);
    public boolean deleteJasa(Long id);
    public JasaDto getJasa(Long id);
}
