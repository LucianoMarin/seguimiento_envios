package com.sumativa1.seguimiento_envios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sumativa1.seguimiento_envios.controller.EnvioNotFoundException;
import com.sumativa1.seguimiento_envios.model.Cliente;
import com.sumativa1.seguimiento_envios.model.Envio;
import com.sumativa1.seguimiento_envios.repository.ClienteRepository;
import com.sumativa1.seguimiento_envios.repository.EnvioRepository;

@ExtendWith(MockitoExtension.class)
public class EnvioServiceImpTest {

    @Mock
    private EnvioRepository envRepository;

    @Mock
    private ClienteRepository cliRepository;

    @InjectMocks
    private EnvioServiceImp envioService;

    private Envio envio;

    @BeforeEach
    void SetUp() {
        Cliente cliente = new Cliente(1L, "18775116-0", "Luciano", "Marin");
        envio = new Envio();

        envio.setId(1L);
        envio.setCodigoSeguimiento("cod00001");
        envio.setCliente(cliente);
        envio.setEstado("ENTREGADO");
        envio.setUbicacionOrigen("Valdivia");
        envio.setUbicacionDestino("Valdivia");
        envio.setUbicacionActual("Valdivia");
        envio.setFechaEnvio(LocalDate.of(2026, 04, 29));
        envio.setFechaEntrega(LocalDate.of(2026, 04, 29));

    }

    @Test
    void testAgregarEnvio() {

        when(envRepository.save(envio)).thenReturn(envio);
        assertEquals(envio, envioService.crearEnvio(envio));
        verify(envRepository).save(envio);
    }

    @Test
    void testEliminarEnvio() {

        when(envRepository.existsById(1L)).thenReturn(true);
        envioService.eliminarEnvio(1L);
        verify(envRepository).deleteById(1L);

    }

    @Test
    void testMostrarEnvios() {

        List<Envio> expected = Arrays.asList(envio);
        when(envRepository.findAll()).thenReturn(expected);
        assertEquals(expected, envioService.mostrarEnvios());

    }

    @Test
    void obtenerEnvioPorId_ok() {
        when(envRepository.findById(1L)).thenReturn(Optional.of(new Envio()));

        Envio resultado = envioService.obtenerEnvioPorId(1L);

        assertNotNull(resultado);
    }

    @Test
    void obtenerEnvioPorId_noExiste() {
        when(envRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EnvioNotFoundException.class, () -> {
            envioService.obtenerEnvioPorId(1L);
        });
    }

    @Test
    void actualizarEnvio_ok() {
        Envio nuevo = new Envio();
        nuevo.setCodigoSeguimiento("cod999");

        when(envRepository.findById(1L)).thenReturn(Optional.of(envio));
        when(envRepository.existsByCodigoSeguimiento("cod999")).thenReturn(false);
        when(envRepository.save(any())).thenReturn(envio);

        Envio resultado = envioService.actualizarEnvio(1L, nuevo);

        assertEquals("cod999", resultado.getCodigoSeguimiento());
    }

    @Test
    void modificarEstados_entregado() {
        when(envRepository.findAll()).thenReturn(List.of(envio));

        envioService.modificarEstados();

        assertEquals("ENTREGADO", envio.getEstado());
    }

    @Test
    void mostrarUbicaciones_ok() {
        when(envRepository.findAll()).thenReturn(List.of(envio));

        List<String> resultado = envioService.mostrarUbicaciones();

        assertEquals(1, resultado.size());
        assertEquals("Valdivia", resultado.get(0));
    }

    @Test
    void obtenerUbicacion_ok() {
        when(envRepository.findAll()).thenReturn(List.of(envio));

        String resultado = envioService.obtenerUbicacion(1L);

        assertEquals("Valdivia", resultado);
    }

}
