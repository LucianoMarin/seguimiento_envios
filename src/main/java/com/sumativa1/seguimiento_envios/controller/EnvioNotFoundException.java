package com.sumativa1.seguimiento_envios.controller;

/* clase excepcion envio no encontrado */
public class EnvioNotFoundException extends RuntimeException{

   public EnvioNotFoundException(String message) {
        super(message);
    }


}
