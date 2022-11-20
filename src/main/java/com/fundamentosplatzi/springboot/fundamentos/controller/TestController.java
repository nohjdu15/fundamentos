package com.fundamentosplatzi.springboot.fundamentos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {
    // ESTA ANOTACION NOS SRIVE PARA ACEPTAR TODAS LAS SOLICITUDES DENTRO DE ESTE METODO A NIVEL HTTTP
    @RequestMapping
    //nos sirve para responder un cuerpo a nivel de servicio
    @ResponseBody
    //retorno de respuesta
    public ResponseEntity<String> function(){
        return new ResponseEntity<>("hello from controller,cam", HttpStatus.OK);
    }

}
