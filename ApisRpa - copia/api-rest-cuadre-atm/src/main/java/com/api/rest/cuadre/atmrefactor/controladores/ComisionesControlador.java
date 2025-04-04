package com.api.rest.cuadre.atmrefactor.controladores;

import com.api.rest.cuadre.atmrefactor.entidades.Comisiones;
import com.api.rest.cuadre.atmrefactor.entidades.RequestDate;
import com.api.rest.cuadre.atmrefactor.servicios.ComisionesServicios;
import com.api.rest.cuadre.utils.Utilerias;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/rpa_comision_diaria_atm")
@Slf4j
public class ComisionesControlador {

    private final ComisionesServicios comisionesServicios;
    private final Utilerias utilerias;
    private static final String SEPARADOR = "**********************************************************************************";

    public ComisionesControlador(ComisionesServicios comisionesServicios, Utilerias utilerias) {
        this.comisionesServicios = comisionesServicios;
        this.utilerias = utilerias;
    }

    @Operation(summary = "comisionesDiarias", description = "Metodo para consulta de Comisiones Diarias")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Solicitud Aceptada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "409", description = "Error 409"),
            @ApiResponse(responseCode = "500", description = "internal server error")})
    @PostMapping("/verid")
    @Transactional(timeout = 360000)
    public ResponseEntity<Object> verId(@RequestBody RequestDate request) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info(SEPARADOR);
        log.info("METODO: Comisiones - F. Consumo: {} F.Desde: {} F.Hasta: {}", utilerias.fechaHora(),
                request.getGdFechaDesde(), request.getGdFechaHasta());

        if (!utilerias.esFechaValida(request.getGdFechaDesde()) || !utilerias.esFechaValida(request.getGdFechaHasta())) {
            log.error("Fecha invalida proporcionada.");
            log.info(SEPARADOR);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fecha inválida proporcionada.");
        }

        CompletableFuture<List<Comisiones>> listaComisionesFuture = comisionesServicios.listaComisiones(request.getGdFechaDesde(),
                request.getGdFechaHasta());

        List<Comisiones> listaComisiones = listaComisionesFuture.get();

        if (listaComisiones == null || listaComisiones.isEmpty()) {
            log.warn("No hay data para Comisiones F.Consumo: {} F.Desde: {} F.Hasta: {}", utilerias.fechaHora(), request.getGdFechaDesde(), request.getGdFechaHasta());
            log.info(SEPARADOR);
            return new ResponseEntity<>("No hay datos para los parámetros ingresados", HttpStatus.NO_CONTENT);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        log.info("Tiempo de ejecucion METODO: Comisiones: {}ms", duration);
        log.info(SEPARADOR);

        return new ResponseEntity<>(listaComisiones, HttpStatus.OK);
    }
}
