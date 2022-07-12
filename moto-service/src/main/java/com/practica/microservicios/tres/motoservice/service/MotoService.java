package com.practica.microservicios.tres.motoservice.service;


import com.practica.microservicios.tres.motoservice.entity.MotoEntity;
import com.practica.microservicios.tres.motoservice.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;


    public MotoEntity crearMoto(MotoEntity motoEntity){

        return motoRepository.save(motoEntity);
    }

    public List<MotoEntity> traerMotos(){

        return motoRepository.findAll();

    }

    public MotoEntity traerMotoId(Integer id){

        return motoRepository.findById(id).orElse(null);

    }

    public List<MotoEntity> buscarMotoUsuarioId(Integer idUsuario){

        return motoRepository.findByUsuarioId(idUsuario);

    }

}
