package com.example.paradiseppk.repository;

import com.example.paradiseppk.entity.Jasa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JasaRepository extends JpaRepository<Jasa, Long> {

    public List<Jasa> findByNamaAcaraContainingIgnoreCase(String namaAcara);

}
