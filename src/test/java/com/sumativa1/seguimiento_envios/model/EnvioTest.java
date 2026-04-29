package com.sumativa1.seguimiento_envios.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnvioTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(1L, "18775116-0", "Luciano", "Marin");

    }

    @Test
    void testGetterAndSetterEnvio() {
        Envio envio = new Envio();

        envio.setId(1L);
        envio.setCodigoSeguimiento("cod00001");
        envio.setCliente(cliente);
        envio.setEstado("ENTREGADO");
        envio.setUbicacionOrigen("Valdivia");
        envio.setUbicacionDestino("Valdivia");
        envio.setUbicacionActual("Valdivia");
        envio.setFechaEnvio(LocalDate.of(2026, 04, 29));
        envio.setFechaEntrega(LocalDate.of(2026, 04, 29));

        assertEquals(1L, envio.getId());
        assertEquals("cod00001", envio.getCodigoSeguimiento());
        assertEquals(cliente, envio.getCliente());
        assertEquals("ENTREGADO", envio.getEstado());
        assertEquals("Valdivia", envio.getUbicacionOrigen());
        assertEquals("Valdivia", envio.getUbicacionDestino());
        assertEquals("Valdivia", envio.getUbicacionActual());
        assertEquals(LocalDate.of(2026, 04, 29), envio.getFechaEnvio());
        assertEquals(LocalDate.of(2026, 04, 29), envio.getFechaEntrega());
    }

    @Test
    void testConstructorEnvio() {
        Envio envio = new Envio(1L, "cod00001", cliente, "ENTREGADO",
                "Valdivia", "Valdivia", "Valdivia",
                LocalDate.of(2026, 04, 29),
                LocalDate.of(2026, 04, 29));

        assertEquals(1L, envio.getId());
        assertEquals("cod00001", envio.getCodigoSeguimiento());
        assertEquals(cliente, envio.getCliente());
        assertEquals("ENTREGADO", envio.getEstado());
        assertEquals("Valdivia", envio.getUbicacionOrigen());
        assertEquals("Valdivia", envio.getUbicacionDestino());
        assertEquals("Valdivia", envio.getUbicacionActual());
        assertEquals(LocalDate.of(2026, 04, 29), envio.getFechaEnvio());
        assertEquals(LocalDate.of(2026, 04, 29), envio.getFechaEntrega());

    }

}
