package com.practica.microservicios.tres.usuarioservice.controller;

import com.practica.microservicios.tres.usuarioservice.entity.UsuarioEntity;
import com.practica.microservicios.tres.usuarioservice.model.CarroModel;
import com.practica.microservicios.tres.usuarioservice.model.MotoModel;
import com.practica.microservicios.tres.usuarioservice.service.UsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<UsuarioEntity>> listarUsuarios(){

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

    @CircuitBreaker(name="carrosCB", fallbackMethod = "fallBackGetCarros")
    @GetMapping("/carros/{usuarioid}")
    public ResponseEntity<List<CarroModel>> traerCarros(@PathVariable("usuarioid") Integer usuarioId){

        UsuarioEntity usuario = usuarioService.traerUsuarioId(usuarioId);

        if (usuario == null) {

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(usuarioService.traerCarros(usuarioId));

    }

    @CircuitBreaker(name="motosCB", fallbackMethod = "fallBackGetMotos")
    @GetMapping("/motos/{usuarioid}")
    public ResponseEntity<List<MotoModel>> traerMotos(@PathVariable("usuarioid") Integer usuarioId){

        UsuarioEntity usuario = usuarioService.traerUsuarioId(usuarioId);

        if (usuario == null) {

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(usuarioService.traerMotos(usuarioId));

    }

    @CircuitBreaker(name="carrosCB", fallbackMethod = "fallBackSaveCarro")
    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<CarroModel> guardarCarro(@PathVariable("usuarioId") Integer usuarioId, @RequestBody CarroModel carroModel){

        CarroModel carro = usuarioService.guardarCarros(usuarioId, carroModel);

        return ResponseEntity.ok(carro);

    }

    @CircuitBreaker(name="motosCB", fallbackMethod = "fallBackSaveMoto")
    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<MotoModel> guardarMotos(@PathVariable("usuarioId") Integer usuarioId, @RequestBody MotoModel motoModel){

        MotoModel moto = usuarioService.guardarMotos(usuarioId, motoModel);

        return ResponseEntity.ok(moto);

    }

    @CircuitBreaker(name="todosCB", fallbackMethod = "fallBackGetTodos") //HACE REFERENCIA A LA INSTANCIA QUE USAREMOS PARA LA TOLERANCIA DE FALLOS
    @GetMapping("/todos/{iduser}")
    public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable("iduser") Integer userId){

        return ResponseEntity.ok(usuarioService.traerVehiculosUsuario(userId));

    }

    public ResponseEntity<List<CarroModel>> fallBackGetCarros(@PathVariable("usuarioId") int usuarioId, RuntimeException e){

        return new ResponseEntity("El usuario: " + usuarioId + " tiene los carros en el taller", HttpStatus.OK);

    }

    public ResponseEntity<List<MotoModel>> fallBackGetMotos(@PathVariable("usuarioId") int usuarioId, RuntimeException e){

        return new ResponseEntity("El usuario: " + usuarioId + " tiene las motos en el taller", HttpStatus.OK);

    }

    public ResponseEntity<Map<String, Object>> fallBackGetTodos(@PathVariable("usuarioId") int usuarioId, RuntimeException e){

        return new ResponseEntity("El usuario: " + usuarioId + " tiene las motos y los carros en el taller", HttpStatus.OK);

    }

    public ResponseEntity<CarroModel> fallBackSaveCarro(@PathVariable("usuarioId") int usuarioId, @RequestBody CarroModel carroModel, RuntimeException e){

        return new ResponseEntity("El usuario: " + usuarioId + " no tiene dinero para lo carros", HttpStatus.OK);

    }

    public ResponseEntity<MotoModel> fallBackSaveMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody MotoModel motoModel,RuntimeException e){

        return new ResponseEntity("El usuario: " + usuarioId + " no tiene dinero para las motos", HttpStatus.OK);

    }

}
