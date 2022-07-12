package com.practica.microservicios.tres.usuarioservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarroModel {

    private String marca;

    private String modelo;

    private Integer usuarioId;


}
