package com.sumativa1.seguimiento_envios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.sumativa1.seguimiento_envios.controller.EnvioDuplicateResourceException;
import com.sumativa1.seguimiento_envios.controller.EnvioNotFoundException;
import com.sumativa1.seguimiento_envios.model.Envio;
import com.sumativa1.seguimiento_envios.repository.EnvioRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

/*
 * CLASE SERVICE O SIMPLEMENTE SERVICEIMP, AQUI SE UNE LA BASE DE DATOS Y SE
 * CREA LA LOGICA DE PROGRAMACION DEL MICROSERVICIO
 */
public class EnvioServiceImp implements EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    /*
     * FUNCION MOSTRAR ENVIO, VALIDA ANTES SI EN LA BD EXISTE ALGUN DATO, SI NO HAY
     * DATOS, TIRA UNA EXCEPCION SINO, MUESTRA UN JSON CON LOS ENVIOS
     */
    @Override
    public List<Envio> mostrarEnvios() {

        log.info("SE EJECUTO SERVICEIMP MOSTRAR TODOS LOS ENVIO");

        List<Envio> envios = envioRepository.findAll();

        if (envios.isEmpty()) {
            throw new EnvioNotFoundException("No existen registros de envios en la BD");
        }

        return envios;
    }

    /*
     * ESTA FUNCION CREA UN ENVIO, SE UTILIZA EL METODO SAVE DE JPA PARA GUARDAR
     * INFORMACION EN LA BD
     */
    @Override
    public Envio crearEnvio(Envio envio) {

        log.info("SE EJECUTO SERVICEIMP CREAR ENVIO");

        try {
            return envioRepository.save(envio);

        } catch (DataIntegrityViolationException e) {
            /* VALIDAR QUE MI CODIGO DE SEGUIMIENTO NO SE DUPLIQUE */
            throw new EnvioDuplicateResourceException("Error al crear envio, revise los campos");
        }

    }

    /*
     * FUNCION ELIMINAR ENVIOS, UTILIZA EL METODO DELETEBYID QUE CONJUNTO AL
     * PARAMETRO ID QUE SE RECIBIRA POR EL CONTROLADOR, ELIMINARA UN ENVIO
     */
    @Override
    public void eliminarEnvio(Long id) {

        log.info("SE EJECUTO SERVICEIMP ELIMINAR ENVIO");

        if (!envioRepository.existsById(id)) {
            throw new EnvioNotFoundException("El envio no existe");
        }

        envioRepository.deleteById(id);

    }

    /*
     * MUESTRA UN ENVIO FILTRADO POR ID, PARA ELLO LO BUSCA DESDE LA BASE DE DATOS
     * CON EL METODO FINDBYID DE JPA.
     */
    @Override
    public Envio obtenerEnvioPorId(Long id) {
        log.info("SE EJECUTO SERVICEIMP OBTENER UN ENVIO ");
        return envioRepository.findById(id).orElseThrow(() -> new EnvioNotFoundException(
                "Envío no encontrado con ID: " + id));

    }

    /*
     * FUNCION QUE ACTUALIZA LOS VALORES EN LA BASE DE DATOS, SE USA EL METODO SAVE
     * COMO AL INSERTAR.
     * PERO LA GRAN DIFERENCIA ES QUE SE VALIDA ANTES LOS DATOS DEL OBJETO.
     * ESTO PERMITIRA AGREGAR INFORMACION EN EL CAMPO DEL OBJETO DONDE EL USUARIO
     * REQUIERA SOLAMENTE.
     * 
     */
    @Override
    public Envio actualizarEnvio(Long id, Envio envio) {

        log.info("SE EJECUTO SERVICEIMP ACTUALIZAR ENVIO");

        Envio existente = envioRepository.findById(id)
                .orElseThrow(() -> new EnvioNotFoundException("No existe el envio"));

        try {

            if (envio.getCodigoSeguimiento() != null) {
                existente.setCodigoSeguimiento(envio.getCodigoSeguimiento());
            }

            if (envio.getEstado() != null) {
                existente.setEstado(envio.getEstado());
            }

            if (envio.getUbicacionOrigen() != null) {
                existente.setUbicacionOrigen(envio.getUbicacionOrigen());
            }

            if (envio.getUbicacionActual() != null) {
                existente.setUbicacionActual(envio.getUbicacionActual());
            }

            if (envio.getUbicacionDestino() != null) {
                existente.setUbicacionDestino(envio.getUbicacionDestino());
            }

            if (envio.getCliente() != null) {
                existente.setCliente(envio.getCliente());
            }
            if (envio.getFechaEntrega() != null) {
                existente.setFechaEntrega(envio.getFechaEntrega());

            }
            if (envio.getFechaEnvio() != null) {
                existente.setFechaEnvio(envio.getFechaEnvio());

            }

            return envioRepository.save(existente);

        } catch (DataIntegrityViolationException e) {
            /* VALIDAR QUE MI CODIGO DE SEGUIMIENTO NO SE DUPLIQUE */
            throw new EnvioDuplicateResourceException("Código de seguimiento ya registrado");
        }

    }

    /* FUNCION PERMITE ACTUALIZAR EL ESTADO DE LOS ENVIOS */
    @Override
    public String modificarEstados() {

        log.info("SE EJECUTO SERVICEIMP ACTUALIZAR ESTADI ENVIOS");

        List<Envio> envio = envioRepository.findAll();

        for (Envio e : envio) {
            if (e.getFechaEntrega() != null && (e.getFechaEntrega().isAfter(e.getFechaEnvio())
                    || e.getFechaEntrega().isEqual(e.getFechaEnvio()))) {

                e.setEstado("ENTREGADO");

            } else if (e.getFechaEnvio() == null) {

                e.setEstado("PENDIENTE");

            } else if (e.getFechaEntrega() != null && e.getFechaEntrega().isBefore(e.getFechaEnvio())) {

                e.setEstado("ERROR");

            } else {

                e.setEstado("EN CAMINO");

            }

            envioRepository.save(e);
        }

        return "";

    }

}
