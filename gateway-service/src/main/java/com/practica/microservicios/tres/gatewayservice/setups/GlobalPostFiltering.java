package com.practica.microservicios.tres.gatewayservice.setups;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalPostFiltering {


    @Bean
    public GlobalFilter  postGlobalFilter(){

        return ((exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() ->{

            }));
        });

    }

}
