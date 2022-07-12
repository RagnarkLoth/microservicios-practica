package com.practica.microservicios.tres.motoservice.controller;

import com.practica.microservicios.tres.motoservice.entity.MotoEntity;
import com.practica.microservicios.tres.motoservice.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motos")
public class MotoRestController {

    @Autowired
    private MotoService motoService;

    @GetMapping
    private ResponseEntity<List<MotoEntity>> listarMotos(){

        List<MotoEntity> motos = motoService.traerMotos();

        if(motos.isEmpty()){

            return ResponseEntity.noContent().build();

        }

        return ResponseEntity.ok(motos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoEntity> obtenerMotoId(@PathVariable("id") Integer id){

        MotoEntity carro = motoService.traerMotoId(id);

        if(carro == null){

            //notFound() es para decir que no se encontro ningun contenido
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(carro);

    }

    @PostMapping
    public ResponseEntity<MotoEntity> crearMoto(@RequestBody MotoEntity motoEntity){

        return ResponseEntity.ok(motoService.crearMoto(motoEntity));

    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<MotoEntity>> traerMotosUsuario(@PathVariable("id") Integer usuarioId){

        List<MotoEntity> moto = motoService.buscarMotoUsuarioId(usuarioId);

        if(moto.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(moto);

    }

}
