package com.sumativa1.seguimiento_envios.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.sumativa1.seguimiento_envios.model.Cliente;
import com.sumativa1.seguimiento_envios.model.Envio;
import com.sumativa1.seguimiento_envios.service.EnvioService;

@ExtendWith(MockitoExtension.class)
public class ControladorClienteTest {


    @Mock
    private EnvioService envioService;

    @InjectMocks
    private ControllerEnvio controller;

    private Envio envio;

    @BeforeEach
    void setUp() {
        Cliente cliente = new Cliente(1L, "18775116-0", "Luciano", "Marin");

        envio = new Envio();
        envio.setId(1L);
        envio.setCodigoSeguimiento("cod00001");
        envio.setCliente(cliente);
        envio.setEstado("ENTREGADO");
        envio.setUbicacionActual("Valdivia");
    }

    @Test
    void mostrarEnvios_ok() {
        when(envioService.mostrarEnvios()).thenReturn(List.of(envio));

        List<Envio> resultado = controller.mostrarEnvios();

        assertEquals(1, resultado.size());
        verify(envioService).mostrarEnvios();
    }

    @Test
    void mostrarUbicaciones_ok() {
        when(envioService.mostrarUbicaciones()).thenReturn(List.of("Valdivia"));

        List<String> resultado = controller.mostrarUbicacion();

        assertEquals(1, resultado.size());
        assertEquals("Valdivia", resultado.get(0));
    }

    @Test
    void obtenerUbicacion_ok() {
        when(envioService.obtenerUbicacion(1L)).thenReturn("Valdivia");

        String resultado = controller.mostrarUbicacion(1L);

        assertEquals("Valdivia", resultado);
    }

    @Test
    void mostrarEnvioPorId_ok() {
        when(envioService.obtenerEnvioPorId(1L)).thenReturn(envio);

        Envio resultado = controller.mostrarEnvioPorId(1L);

        assertEquals("cod00001", resultado.getCodigoSeguimiento());
    }

    @Test
    void crearEnvio_ok() {
        when(envioService.crearEnvio(envio)).thenReturn(envio);

        Envio resultado = controller.crearEnvio(envio);

        assertNotNull(resultado);
        verify(envioService).crearEnvio(envio);
    }

    @Test
    void modificarEnvio_ok() {
        when(envioService.actualizarEnvio(1L, envio)).thenReturn(envio);

        Envio resultado = controller.modificarEnvio(1L, envio);

        assertEquals("cod00001", resultado.getCodigoSeguimiento());
    }

    @Test
    void eliminarEnvio_ok() {
        doNothing().when(envioService).eliminarEnvio(1L);

        controller.eliminarEnvio(1L);

        verify(envioService).eliminarEnvio(1L);
    }

@Test
void actualizarEstados_ok() {
    when(envioService.modificarEstados()).thenReturn("");

    ResponseEntity<String> response = controller.actualizarEstados();

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("Estados actualizados", response.getBody());
}

}
