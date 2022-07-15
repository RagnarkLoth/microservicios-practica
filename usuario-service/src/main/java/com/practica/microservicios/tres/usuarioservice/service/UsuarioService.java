package com.practica.microservicios.tres.usuarioservice.service;

import com.practica.microservicios.tres.usuarioservice.entity.UsuarioEntity;
import com.practica.microservicios.tres.usuarioservice.feignclients.CarroFeignClient;
import com.practica.microservicios.tres.usuarioservice.feignclients.MotoFeignClient;
import com.practica.microservicios.tres.usuarioservice.model.CarroModel;
import com.practica.microservicios.tres.usuarioservice.model.MotoModel;
import com.practica.microservicios.tres.usuarioservice.repository.UsuarioRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class UsuarioService {

    @Autowired
    private CarroFeignClient carroFeignClient;

    @Autowired
    private MotoFeignClient motoFeignClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //COMUNICACION DE MICROSERVICIOS REST TEMPLATE
    public List<CarroModel> traerCarros(int usuarioId){

         return restTemplate.getForObject("http://coche-service/api/carros/usuario/" + usuarioId, List.class);

    }

    public List<MotoModel> traerMotos(int usuarioId){

        return restTemplate.getForObject("http://moto-service/api/motos/usuario/" + usuarioId, List.class);

    }
    /*HASTA AQUI*/

    /*COMUNICACION MICROSERVICIOS CON CLIENT*/
    public  CarroModel guardarCarros(Integer usuarioId, CarroModel carroModel){

        carroModel.setUsuarioId(usuarioId);

        return carroFeignClient.save(carroModel);

    }

    public  MotoModel guardarMotos(Integer usuarioId, MotoModel motoModel){

        motoModel.setUsuarioId(usuarioId);

        return motoFeignClient.save(motoModel);

    }

    public Map<String, Object> traerVehiculosUsuario(Integer usuarioId){

        Map<String, Object> resultado = new HashMap<>();

        UsuarioEntity usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if(usuario == null){

            resultado.put("Mensaje", "El usuario no existe");
            return resultado;

        }

        resultado.put("Usuario", usuario);

        if(carroFeignClient.getCarros(usuarioId) == null){

            resultado.put("Carros", "El usuario no tiene carros");

        }else{
            resultado.put("Carros", carroFeignClient.getCarros(usuarioId));
        }

        if(motoFeignClient.getMotos(usuarioId) == null){
            resultado.put("Motos", "El usuario no tiene motos");
        }else {
            resultado.put("Motos", motoFeignClient.getMotos(usuarioId));

        }

        return resultado;

    }

    //HASTA AQUI

    public UsuarioEntity crearUsuario(UsuarioEntity usuarioEntity){

        return usuarioRepository.save(usuarioEntity);
    }

    public List<UsuarioEntity> traerUsuarios(){

        return usuarioRepository.findAll();

    }

    public UsuarioEntity traerUsuarioId(Integer id){

        return usuarioRepository.findById(id).orElse(null);

    }

}
