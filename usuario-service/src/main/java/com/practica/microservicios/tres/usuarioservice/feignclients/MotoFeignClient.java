package com.practica.microservicios.tres.usuarioservice.feignclients;

import com.practica.microservicios.tres.usuarioservice.model.CarroModel;
import com.practica.microservicios.tres.usuarioservice.model.MotoModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "moto-service", url = "http://localhost:8003")
@RequestMapping("/api/motos")
public interface MotoFeignClient {

    @PostMapping
    MotoModel save(@RequestBody MotoModel motoModel);

    @GetMapping("/usuario/{id}")
    List<MotoModel> getMotos(@PathVariable("id") Integer usuarioId);

}
