package com.practica.microservicios.tres.usuarioservice.repository;

import com.practica.microservicios.tres.usuarioservice.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {



}
