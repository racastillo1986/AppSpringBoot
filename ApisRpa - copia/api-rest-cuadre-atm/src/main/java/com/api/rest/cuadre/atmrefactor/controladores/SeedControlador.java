package com.api.rest.cuadre.atmrefactor.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.cuadre.atmrefactor.entidades.RequestDate;
import com.api.rest.cuadre.atmrefactor.entidades.Seed;
import com.api.rest.cuadre.atmrefactor.servicios.SeedServicio;
import com.api.rest.cuadre.utils.Utilerias;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/seedValores")
@Slf4j
public class SeedControlador {

	private final SeedServicio seedServicio;
	private final Utilerias utilerias;
	private static final String SEPARADOR = "**********************************************************************************";

	public SeedControlador(SeedServicio seedServicio, Utilerias utilerias) {

		this.seedServicio = seedServicio;
		this.utilerias = utilerias;

	}

	@Operation(summary = "seedValores", description = "Metodo para consulta de SeedBilling")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Solicitud Aceptada"),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida"),
			@ApiResponse(responseCode = "409", description = "Error 409"),
			@ApiResponse(responseCode = "500", description = "internal server error") })
	@PostMapping("/verid")
	public ResponseEntity<Object> valoresSeed(@RequestBody RequestDate request) {
		long startTime = System.currentTimeMillis();
		log.info(SEPARADOR);
		log.info("* METODO: valores Seed - F.Consumo: {} Identificacion: {} F.Desde: {} F.Hasta: {}",
				utilerias.fechaHora(), request.getGvIdentificacion(), request.getGdFechaDesde(),
				request.getGdFechaHasta());

		if (utilerias.esFechaValida(request.getGdFechaDesde()) || utilerias.esFechaValida(request.getGdFechaHasta())) {
			log.error("Fecha invalida proporcionada.");
			log.info(SEPARADOR);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fecha inválida proporcionada.");
		}

		List<Seed> seedValores = seedServicio.listaSeed(request.getGvIdentificacion(), request.getGdFechaDesde(),
				request.getGdFechaHasta());

		if (seedValores.isEmpty()) {
			log.info("No hay data para valoresSeed - F.Consumo: {} Identificacion: {} F.Desde: {} F.Hasta: {}",
					utilerias.fechaHora(), request.getGvIdentificacion(), request.getGdFechaDesde(), request.getGdFechaHasta());
			log.info(SEPARADOR);
			return new ResponseEntity<>("No hay datos para los parámetros ingresados", HttpStatus.NO_CONTENT);
		}

		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		log.info("Tiempo de ejecucion valoresSeed {}ms: ", duration);
		log.info(SEPARADOR);
		return new ResponseEntity<>(seedValores, HttpStatus.OK);
	}

}
