package com.practica.microservicios.tres.cocheservice.service;

import com.practica.microservicios.tres.cocheservice.entity.CarroEntity;
import com.practica.microservicios.tres.cocheservice.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;


    public CarroEntity crearCarro(CarroEntity carroEntity){

        return carroRepository.save(carroEntity);
    }

    public List<CarroEntity> traerCarros(){

        return carroRepository.findAll();

    }

    public CarroEntity traerCarroId(Integer id){

        return carroRepository.findById(id).orElse(null);

    }

    public List<CarroEntity> buscarCarroIdUsuario(Integer idUsuario){

        return carroRepository.findByUsuarioId(idUsuario);

    }


}
