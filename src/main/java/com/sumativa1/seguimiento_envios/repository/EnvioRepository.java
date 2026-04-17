package com.sumativa1.seguimiento_envios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumativa1.seguimiento_envios.model.Envio;


/* INTERFACE QUE LLAMARA EL REPOSITORIO DE JPA */
public interface EnvioRepository extends JpaRepository<Envio, Long> {
}
