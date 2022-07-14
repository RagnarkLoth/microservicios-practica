package com.practica.microservicios.tres.usuarioservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced //ES PARA EL BALANCEO DE CARGA
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
