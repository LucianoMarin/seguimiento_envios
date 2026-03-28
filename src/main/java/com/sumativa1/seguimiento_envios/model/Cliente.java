package com.sumativa1.seguimiento_envios.model;

/* CLASE CLIENTE, NO SE OCUPARA POR EL MOMENTO
    SOLO QUIERO MOSTRAR LA ESTRUCTURA.
*/

public class Cliente {

    private String rut;
    private String nombre;
    private String apellido;

    
    public Cliente(){}

    public Cliente(String rut,String nombre,String apellido){

            this.rut=rut;
            this.nombre=nombre;
            this.apellido=apellido;

        }



    public String getRut() {return this.rut;}

    public void setRut(String rut) {this.rut = rut;}

    public String getNombre() {return this.nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return this.apellido;}

    public void setApellido(String apellido) {this.apellido = apellido;}



}
