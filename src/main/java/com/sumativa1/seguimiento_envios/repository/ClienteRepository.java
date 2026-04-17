package com.sumativa1.seguimiento_envios.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sumativa1.seguimiento_envios.model.Cliente;


public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
    
}
