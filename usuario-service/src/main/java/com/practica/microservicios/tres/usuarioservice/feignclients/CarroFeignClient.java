package com.practica.microservicios.tres.usuarioservice.feignclients;

import com.practica.microservicios.tres.usuarioservice.model.CarroModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "coche-service", url = "http://localhost:8002")
@RequestMapping("/api/carros")
public interface CarroFeignClient {

     @PostMapping
     CarroModel save(@RequestBody CarroModel carroModel);

     @GetMapping("/usuario/{usuarioId}")
     List<CarroModel> getCarros(@PathVariable ("usuarioId") Integer usuarioId);

}
