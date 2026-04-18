package com.sumativa1.seguimiento_envios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumativa1.seguimiento_envios.model.Envio;
import com.sumativa1.seguimiento_envios.service.EnvioService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/envio")

@CrossOrigin(origins = "*")

/* controlador envio */
public class ControllerEnvio {

    /* creamos un logger */
    private static Logger log = LoggerFactory.getLogger(ControllerEnvio.class);

    @Autowired
    private EnvioService envioService;

    /* endpoint GET obtener envios */
    @GetMapping
    public List<Envio> mostrarEnvios() {
        /* log de endpoint mostrar envios */
        log.info("GET /envio");
        log.info("Retorna todos los envios");

        return envioService.mostrarEnvios();
    }

    /* endpoint GET obtener un envio */
    @GetMapping("/{id}")
    public Envio mostrarEnvioPorId(@PathVariable Long id) {
        /* log de endpoint mostrar envio */
        log.info("GET /envio/id");
        log.info("Retorna un solo envio");

        return envioService.obtenerEnvioPorId(id);

    }

    /* endpoint POST crear un envio */

    /*
     * AL MOMENTO DE EJECUTAR EL ENDPOINT, CONSIDERAR QUE SE DEBE ASOCIAR AL CLIENTE
     * POR LO CUAL ESTE MICROSERVICIO CUENTA CON 3 INSERCIONES EN LA TABLA CLIENTE
     * CON ID: 1-> LUCIANO ,2 -> LUJANA ,3 -> MARIO
     * 
     */

    @PostMapping
    public Envio crearEnvio(@RequestBody Envio envio) {
        /* log de endpoint crear envio */
        log.info("POST /envio");
        log.info("Guarda un envio en la BD");

        return envioService.crearEnvio(envio);

    }

    /* endpoint PUT editar envio */
    @PutMapping("/{id}")
    public Envio modificarEnvio(@PathVariable Long id, @RequestBody Envio envio) {
        /* log de endpoint editar envio */
        log.info("PUT /envio");
        log.info("Edita un envio en la BD");

        return envioService.actualizarEnvio(id, envio);

    }

    /* endpoint eliminar envio */
    @DeleteMapping("/{id}")
    public void eliminarEnvio(@PathVariable Long id) {
        /* log de endpoint eliminar envio */
        log.info("DELETE /envio/id");
        log.info("Elimina un envio en la BD");

        envioService.eliminarEnvio(id);

    }

    /*
     * ENDPOINT LLAMA LA FUNCION EN SERVICE QUE ACTUALIZA LOS ESTADOS DE LOS ENVIOS.
     * PARA ELLO SE DEBE SOLO EJECUTAR COMO PUT EN POSTMAN LA URL
     * /ACTUALIZAR-ESTADOS
     */

    @PutMapping("/actualizar-estados")
    public ResponseEntity<String> actualizarEstados() {
        log.info("PUT /envio/actualizar-estados");
        log.info("actualiza los estados de envios en BD");

        envioService.modificarEstados();

        return ResponseEntity.ok("Estados actualizados");
    }

}
