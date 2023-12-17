package com.example.paradiseppk.service;

import com.example.paradiseppk.dto.JasaDto;
import com.example.paradiseppk.entity.EStatus;
import com.example.paradiseppk.entity.Jasa;
import com.example.paradiseppk.mapper.JasaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import com.example.paradiseppk.repository.JasaRepository;

@Service
public class JasaServiceImpl implements JasaService{
    @Autowired
    private JasaRepository jasaRepository;
    
    @Override
    public JasaDto createJasa(JasaDto jasaDto) {
        Jasa jasa = jasaRepository.save(JasaMapper.mapToJasa(jasaDto));
        jasa.setStatus(EStatus.MENUNGGU_PERSETUJUAN);
        return JasaMapper.mapToJasaDto(jasa);
    }
    
    @Override
    public List<JasaDto> getJasas() {
        List<Jasa> jasas = jasaRepository.findAll();
        List<JasaDto> jasaDtos = jasas.stream()
        .map((product) -> (JasaMapper.mapToJasaDto(product)))
        .collect(Collectors.toList());
        return jasaDtos;
    }
    
    @Override
    public List<JasaDto> searchJasas(String query){
        List<Jasa> jasas = jasaRepository.findByNamaAcaraContainingIgnoreCase(query);
        List<JasaDto> jasaDtos = jasas.stream()
                .map((jasa) -> (JasaMapper.mapToJasaDto(jasa)))
                .collect(Collectors.toList());
        
        return jasaDtos;
    }
    
    @Override
    public JasaDto getJasa(Long id) {
        Jasa jasa = jasaRepository.getReferenceById(id);
        return JasaMapper.mapToJasaDto(jasa);
    }
    
    @Override
    public JasaDto updateJasa(JasaDto jasaDto) {
        Jasa jasa = jasaRepository.save(JasaMapper.mapToJasa(jasaDto));
        return JasaMapper.mapToJasaDto(jasa);
    }
    
    @Override
    public boolean deleteJasa(Long id) {
        jasaRepository.deleteById(id);
        return true;
    }
}
