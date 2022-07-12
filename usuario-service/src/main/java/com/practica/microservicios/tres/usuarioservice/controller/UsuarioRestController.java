package com.practica.microservicios.tres.usuarioservice.controller;

import com.practica.microservicios.tres.usuarioservice.entity.UsuarioEntity;
import com.practica.microservicios.tres.usuarioservice.model.CarroModel;
import com.practica.microservicios.tres.usuarioservice.model.MotoModel;
import com.practica.microservicios.tres.usuarioservice.service.UsuarioService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    private ResponseEntity<List<UsuarioEntity>> listarUsuarios(){

        List<UsuarioEntity> usuarios = usuarioService.traerUsuarios();

        if(usuarios.isEmpty()){

            return ResponseEntity.noContent().build();

        }

        return ResponseEntity.ok(usuarios);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> obtenerUsuario(@PathVariable("id") Integer id){

        UsuarioEntity usuario = usuarioService.traerUsuarioId(id);

        if(usuario == null){

            //notFound() es para decir que no se encontro ningun contenido
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(usuario);

    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> crearUsuario(@RequestBody UsuarioEntity usuarioEntity){

        UsuarioEntity usuario = usuarioService.crearUsuario(usuarioEntity);

        log.info(usuarioEntity.getId());

        return ResponseEntity.ok(usuario);

    }

    @GetMapping("/carros/{usuarioid}")
    public ResponseEntity<List<CarroModel>> traerCarros(@PathVariable("usuarioid") Integer usuarioId){

        UsuarioEntity usuario = usuarioService.traerUsuarioId(usuarioId);

        if (usuario == null) {

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(usuarioService.traerCarros(usuarioId));

    }

    @GetMapping("/motos/{usuarioid}")
    public ResponseEntity<List<MotoModel>> traerMotos(@PathVariable("usuarioid") Integer usuarioId){

        UsuarioEntity usuario = usuarioService.traerUsuarioId(usuarioId);

        if (usuario == null) {

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(usuarioService.traerMotos(usuarioId));

    }

    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<CarroModel> guardarCarro(@PathVariable("usuarioId") Integer usuarioId, @RequestBody CarroModel carroModel){

        CarroModel carro = usuarioService.guardarCarros(usuarioId, carroModel);

        return ResponseEntity.ok(carro);

    }


    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<MotoModel> guardarMotos(@PathVariable("usuarioId") Integer usuarioId, @RequestBody MotoModel motoModel){

        MotoModel moto = usuarioService.guardarMotos(usuarioId, motoModel);

        return ResponseEntity.ok(moto);

    }

    @GetMapping("/todos/{iduser}")
    public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable("iduser") Integer userId){

        return ResponseEntity.ok(usuarioService.traerVehiculosUsuario(userId));

    }

}
