package com.practica.microservicios.tres.motoservice.repository;

import com.practica.microservicios.tres.motoservice.entity.MotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<MotoEntity, Integer> {
    List<MotoEntity> findByUsuarioId(Integer usuarioId);

}
