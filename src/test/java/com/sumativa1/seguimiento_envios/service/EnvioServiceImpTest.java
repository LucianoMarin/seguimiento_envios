package com.sumativa1.seguimiento_envios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    void SetUp(){
        Cliente cliente = new Cliente(1L,"18775116-0","Luciano","Marin");
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
    void testAgregarEnvio(){
        when(envRepository.save(envio)).thenReturn(envio);
        assertEquals(envio, envioService.crearEnvio(envio));
        verify(envRepository).save(envio);
    }



    @Test
    void testEliminarEnvio(){
        when(envRepository.existsById(1L)).thenReturn(true);
        envioService.eliminarEnvio(1L);
        verify(envRepository).deleteById(1L);

    }

    

}
