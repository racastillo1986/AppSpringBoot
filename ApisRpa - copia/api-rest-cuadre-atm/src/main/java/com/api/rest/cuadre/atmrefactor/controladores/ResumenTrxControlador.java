package com.api.rest.cuadre.atmrefactor.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.cuadre.atmrefactor.entidades.RequestDate;
import com.api.rest.cuadre.atmrefactor.entidades.ResumenTrx;
import com.api.rest.cuadre.atmrefactor.servicios.ResumenTrxServicio;
import com.api.rest.cuadre.utils.Utilerias;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/rpa_resumen_trx_atm")
@Slf4j
public class ResumenTrxControlador {

	private final ResumenTrxServicio resumenTrxServicio;
	private final Utilerias utilerias;
	private static final String SEPARADOR = "**********************************************************";

	public ResumenTrxControlador(ResumenTrxServicio resumenTrxServicio, Utilerias utilerias) {
		this.resumenTrxServicio = resumenTrxServicio;
		this.utilerias = utilerias;
	}

	@Operation(summary = "resumenTrx", description = "Metodo para consulta de Resumen de Trx")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Solicitud Aceptada"),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida"),
			@ApiResponse(responseCode = "409", description = "Error 409"),
			@ApiResponse(responseCode = "500", description = "internal server error") })
	@PostMapping("/verid")
	public ResponseEntity<Object> resumenTrx(@RequestBody RequestDate request) {

		long startTime = System.currentTimeMillis();
		log.info(SEPARADOR);
		log.info("* METODO: resumentrx - F.Consumo: {} F.Desde: {} F.Hasta: {}", utilerias.fechaHora(),	request.getGdFechaDesde(), request.getGdFechaHasta());

		if (utilerias.esFechaValida(request.getGdFechaDesde()) || utilerias.esFechaValida(request.getGdFechaHasta())) {
			log.error("Fecha invalida proporcionada.");
			log.info(SEPARADOR);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fecha invalida proporcionada.");
		}

		List<ResumenTrx> listaResumenTrx = resumenTrxServicio.listaResumen(request.getGdFechaDesde(), request.getGdFechaHasta());

		if (listaResumenTrx.isEmpty()) {
			log.info("No hay data para resumen trx - F.Consumo: {} F.Desde: {} F.Hasta: {}", utilerias.fechaHora(), request.getGdFechaDesde(), request.getGdFechaHasta());
			log.info(SEPARADOR);
			return new ResponseEntity<>("[]", HttpStatus.NO_CONTENT);
		}

		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		log.info("Tiempo de ejecucion resumentrx: {}ms", duration);
		log.info(SEPARADOR);

		return new ResponseEntity<>(listaResumenTrx, HttpStatus.OK);
	}

}
