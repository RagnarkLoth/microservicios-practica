package com.practica.microservicios.tres.usuarioservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MotoModel {

    private String marca;

    private String modelo;

    private Integer usuarioId;

}
