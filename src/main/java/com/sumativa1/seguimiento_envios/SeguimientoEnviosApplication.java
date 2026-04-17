package com.sumativa1.seguimiento_envios;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sumativa1.seguimiento_envios.model.Cliente;
import com.sumativa1.seguimiento_envios.repository.ClienteRepository;

@SpringBootApplication
public class SeguimientoEnviosApplication {

	public static void main(String[] args) {

		SpringApplication.run(SeguimientoEnviosApplication.class, args);

	}

	/* funcion commandline, me permite agregar filas a las tablas cliente en el primer inicio
		se valida que no se repita las filas por cada inicio con repo.count y en el modelo con Column unique
	*/
	@Bean
	CommandLineRunner initData(ClienteRepository repo) {
		return args -> {
			if (repo.count() == 0) {
				Cliente c1 = new Cliente();
				c1.setRut("18775116-0");
				c1.setNombre("Luciano");
				c1.setApellido("Marin");

				Cliente c2 = new Cliente();
				c2.setRut("9415449-9");
				c2.setNombre("Lujana");
				c2.setApellido("Villanueva");

				Cliente c3 = new Cliente();
				c3.setRut("9862879-7");
				c3.setNombre("Mario");
				c3.setApellido("Marin");

				repo.saveAll(List.of(c1, c2,c3));
			}
		};
	}

}
