package com.practica.microservicios.tres.cocheservice.repository;

import com.practica.microservicios.tres.cocheservice.entity.CarroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<CarroEntity, Integer> {


    List<CarroEntity> findByUsuarioId(Integer usuarioId);

}
