package com.sumativa1.seguimiento_envios.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ClienteTest {



    @Test
    void testConstructorCliente(){
        Cliente cliente = new Cliente(1L,"18775116-0","Luciano","Marin");

        assertEquals(1L, cliente.getId());
        assertEquals("18775116-0", cliente.getRut());
        assertEquals("Luciano", cliente.getNombre());
        assertEquals("Marin", cliente.getApellido());
        assertEquals(cliente, cliente);


    }


    @Test

    void testGetterAndSetterCliente(){
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setRut("18775116-0");
        cliente.setNombre("Luciano");
        cliente.setApellido("Marin");


        assertEquals(1L, cliente.getId());
        assertEquals("18775116-0", cliente.getRut());
        assertEquals("Luciano", cliente.getNombre());
        assertEquals("Marin", cliente.getApellido());

    }
  





}
