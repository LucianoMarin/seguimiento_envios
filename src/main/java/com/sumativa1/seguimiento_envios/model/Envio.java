package com.sumativa1.seguimiento_envios.model;

import java.time.LocalDate;
/* CLASE ENVIOS, SE USARA EN LA PROBLEMATICA PARA CREAR LA LISTA DE ENVIOS */

/* SE CONSIDERA AGREGAR ID Y CODIGOSEGUIMIENTO, SI BIEN AMBOS SON IDENTIFICADORES, EL ID ES PARA USO INTERNO 
    Y EL CODIGO DE SEGUIMIENTO SERIA PARA EL CLIENTE.
*/

/* ATRIBUTOS DE LA CLASE, PRIVADOS */
public class Envio {
    private int id;
    private String codigoSeguimiento;
    private String rutCliente;
    private String estado;
    private String ubicacionOrigen;
    private String ubicacionDestino;
    private String ubicacionActual;
    private LocalDate fechaEnvio;
    private LocalDate fechaEntrega;

/* CONSTRUCTOR PARAMETRIZADO */
    public Envio(int id, String codigoSeguimiento, String rutCliente, String estado, String ubicacionOrigen, String ubicacionDestino,String ubicacionActual,
            LocalDate fechaEnvio, LocalDate fechaEntrega) {

        this.id = id;
        this.codigoSeguimiento = codigoSeguimiento;
        this.rutCliente = rutCliente;
        this.estado = estado;
        this.ubicacionOrigen = ubicacionOrigen;
        this.ubicacionDestino=ubicacionDestino;
        this.ubicacionActual=ubicacionActual;
        this.fechaEnvio = fechaEnvio;
        this.fechaEntrega = fechaEntrega;

    }

    /* INICIAN LOS GETTER AND SETTER */
    public LocalDate getFechaEnvio() {
        return this.fechaEnvio;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public LocalDate getFechaEntrega() {
        return this.fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoSeguimiento() {
        return this.codigoSeguimiento;
    }

    public void setCodigoSeguimiento(String codigoSeguimiento) {
        this.codigoSeguimiento = codigoSeguimiento;
    }

    public String getRutCliente() {
        return this.rutCliente;
    }

    public void setRutCliente(String rutCliente) {
        this.rutCliente = rutCliente;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacionOrigen() {
        return this.ubicacionOrigen;
    }

    public void setUbicacionOrigen(String ubicacionOrigen) {
        this.ubicacionOrigen = ubicacionOrigen;
    }


    public String getUbicacionDestino() {
        return this.ubicacionDestino;
    }

    public void setUbicacionDestino(String ubicacionDestino) {
        this.ubicacionDestino = ubicacionDestino;
    }


    public String getUbicacionActual() {
        return this.ubicacionActual;
    }

    public void setUbicacionActual(String ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }




}
