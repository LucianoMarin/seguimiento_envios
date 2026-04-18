package com.sumativa1.seguimiento_envios.model;

import java.time.LocalDate;
/* CLASE ENVIOS, SE USARA EN LA PROBLEMATICA PARA CREAR LA LISTA DE ENVIOS */


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

/* SE CONSIDERA AGREGAR ID Y CODIGOSEGUIMIENTO, SI BIEN AMBOS SON IDENTIFICADORES, EL ID ES PARA USO INTERNO 
    Y EL CODIGO DE SEGUIMIENTO SERIA PARA EL CLIENTE.
*/

/* ATRIBUTOS DE LA CLASE, PRIVADOS */


@Entity
@Table(name="envio")

public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[a-zA-Z0-9]+$",message = "Codigo solo acepta Alfanumericos")
    
    /* CODIGO DE SEGUIMIENTO, NO PUEDE SER NULO NI REPETIRSE (ES UNICO)*/
    @NotBlank(message = "El codigo de seguimiento no puede estar vacio")
    @Size(min = 1, max = 30, message = "El codigo de seguimiento debe cumplir con al menos 1 caracter y max 30")
    @Column(unique = true, name="codigo_seguimiento")   
    private String codigoSeguimiento;

    @ManyToOne
    @NotNull(message = "El cliente es obligatorio para crear un envío")
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    /* SE CREA COLUMNA ESTADO, PERMITE SOLO INGRESAR 5 ESTADOS PREDEFINIDOS POR REGEXP, NO PUEDE SER UN CAMPO VACIO */
    @Pattern(regexp = "^(?i)(PENDIENTE|EN CAMINO|ENTREGADO|CANCELADO|ERROR)$", message = "Estado no valido, recuerde que estan pre-establecidos como PENDIENTE|EN CAMINO|ENTREGADO|CANCELADO|ERROR ")
    @Size(min = 1, max = 30, message = "Debe ingresar un valor para el estado")
    @NotBlank(message = "El estado del envio no puede estar vacio")
    @Column(name="estado")
    private String estado;

    /* SE CREA LA COLUMNA UBICACION_ORIGEN, SE VALIDA QUE NO SEA NULL, SE ASIGNA REGEXP QUE EVITA COLOCAR CARACTERES */
    @Pattern(regexp = "^[a-zA-Z]+( [a-zA-Z]+)*$", message = "Ingrese solo caracteres para la ubicacion origen")
    @Size(min = 1, max = 100, message = "Debe cumplir con el valor min y max del campo ubicacion origen")
    @NotBlank(message = "La ubicacion de origen no puede estar vacia")
    @Column(name="ubicacion_origen")
    private String ubicacionOrigen;

    /* SE CREA COLUMNA UBICACION_DESTINO, SE VALIDA QUE NO SEA NULL, SE USA REGEX PARA INGRESAR SOLO UNA CADENA */
    @Pattern(regexp = "^[a-zA-Z]+( [a-zA-Z]+)*$", message = "Ingrese solo caracteres para la ubicacion destino")
    @Size(min = 1, max = 100, message = "Debe cumplir con el valor min y max del campo ubicacion destino")
    @NotBlank(message = "La ubicacion de destino no puede estar vacia")
    @Column(name="ubicacion_destino")
    private String ubicacionDestino;
 
    /* SE CREA COLUMNA UBICACION_ACTUAL, SE VALIDA QUE NO SEA NULL, SE USA REGEX PARA INGRESAR SOLO UNA CADENA */
    @Pattern(regexp = "^[a-zA-Z]+( [a-zA-Z]+)*$", message = "Ingrese solo caracteres para la ubicacion actual")
    @Size(min = 1, max = 100, message = "Debe cumplir con el valor min y max del campo ubicacion actual")
    @NotBlank(message = "La ubicacion actual no puede estar vacia")
    @Column(name="ubicacion_actual")
    private String ubicacionActual;

    /* FECHA ENVIO, SOLO VALIDADO QUE NO SEA VACIO */
    @NotNull
    @Column(name="fecha_envio")
    private LocalDate fechaEnvio;

    
    /* FECHA ENTREGA PUEDE SER NULO, POR LO CUAL NO SE VALIDA CON NOTNULL
        SEGUN LA LOGICA UN ENVIO PUEDE QUE NO TENGA AUN UNA FECHA ESTIMADA DE ENTREGA
    */
 
    @Column(name="fecha_entrega")
    private LocalDate fechaEntrega;

/* CONSTRUCTOR PARAMETRIZADO */

    public Envio(){}

    public Envio(Long id, String codigoSeguimiento, Cliente cliente, String estado, String ubicacionOrigen, String ubicacionDestino,String ubicacionActual,
            LocalDate fechaEnvio, LocalDate fechaEntrega) {

        this.id = id;
        this.codigoSeguimiento = codigoSeguimiento;
        this.cliente = cliente;
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoSeguimiento() {
        return this.codigoSeguimiento;
    }

    public void setCodigoSeguimiento(String codigoSeguimiento) {
        this.codigoSeguimiento = codigoSeguimiento;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {

        this.estado = (estado != null) ? estado.toUpperCase() : null;
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
