package com.sumativa1.seguimiento_envios.controller;


/* clase excepcion oara envios duplicados */
public class EnvioDuplicateResourceException extends RuntimeException {

    public EnvioDuplicateResourceException(String message) {
        super(message);

    }
}
