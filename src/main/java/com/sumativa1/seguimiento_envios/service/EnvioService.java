package com.sumativa1.seguimiento_envios.service;

import java.util.List;

import com.sumativa1.seguimiento_envios.model.Envio;

public interface EnvioService {

    List<Envio> mostrarEnvios();

    Envio obtenerEnvioPorId(Long id);

    Envio crearEnvio(Envio envio);

    Envio actualizarEnvio(Long id, Envio envio);

    void eliminarEnvio(Long id);

    String modificarEstados();

    
    //Envio obtenerPorCodigoSeguimiento(String codigo);

}
