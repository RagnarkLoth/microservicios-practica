package com.practica.microservicios.tres.cocheservice.controller;

import com.practica.microservicios.tres.cocheservice.entity.CarroEntity;
import com.practica.microservicios.tres.cocheservice.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
public class CarroRestController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    private ResponseEntity<List<CarroEntity>> listarCarros(){

        List<CarroEntity> carros = carroService.traerCarros();

        if(carros.isEmpty()){

            return ResponseEntity.noContent().build();

        }

        return ResponseEntity.ok(carros);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroEntity> obtenerCarros(@PathVariable("id") Integer id){

        CarroEntity carro = carroService.traerCarroId(id);

        if(carro == null){

            //notFound() es para decir que no se encontro ningun contenido
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(carro);

    }

    @PostMapping
    public ResponseEntity<CarroEntity> crearCarro(@RequestBody CarroEntity carroEntity){

        return ResponseEntity.ok(carroService.crearCarro(carroEntity));

    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<CarroEntity>> tearCarrosUsuario(@PathVariable("id") Integer usuarioId){

        List<CarroEntity> carro = carroService.buscarCarroIdUsuario(usuarioId);

        if(carro.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(carro);

    }

}
