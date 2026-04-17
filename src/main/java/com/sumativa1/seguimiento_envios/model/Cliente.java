package com.sumativa1.seguimiento_envios.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/* CLASE CLIENTE, EN ESTE CASO SE IMPLEMENTARA EN EL MICRO
PERO NO SE IMPLEMENTARAN LOS ENDPOINTS.
POR LO CUAL LA TABLA EXISTIRA EN EL SISTEMA, SE RELACIONARA CON CLASE ENVIO
PERO POR MEDIO DE INGRESOS ANTES INSERTADOS EN TABLA.

*/

@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[0-9]{7,8}-[0-9kK]$", message="Rut no valido, recuerde colocar el - y codigo verificador")
    @Size(min = 1, max = 15, message = "Rut no cumple con el tamaño correcto")
    @NotBlank(message = "El rut del cliente no puede estar vacio")
    @Column(unique=true,name="rut")   
    private String rut;

    @Pattern(regexp = "^[a-zA-Z]+( [a-zA-Z]+)*$", message = "Ingrese solo letras para la nombre")
    @Size(min = 1, max = 100, message = "Debe cumplir con el valor min y max del campo nombre")
    @Column(name="nombre")
    private String nombre;

    @Pattern(regexp = "^[a-zA-Z]+( [a-zA-Z]+)*$", message = "Ingrese solo letras para la apellido")
    @Size(min = 1, max = 100, message = "Debe cumplir con el valor min y max del campo apellido")
    @Column(name="apellido")
    private String apellido;

    
    public Cliente(){}

    public Cliente(String rut,String nombre,String apellido){

            this.rut=rut;
            this.nombre=nombre;
            this.apellido=apellido;

        }

    public Long getId(){

        return this.id;
    }

    public void setId(Long id){
        this.id=id;

    }


    public String getRut() {return this.rut;}

    public void setRut(String rut) {this.rut = rut;}

    public String getNombre() {return this.nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return this.apellido;}

    public void setApellido(String apellido) {this.apellido = apellido;}



}
