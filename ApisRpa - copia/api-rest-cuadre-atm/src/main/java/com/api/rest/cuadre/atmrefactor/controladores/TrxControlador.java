package com.api.rest.cuadre.atmrefactor.controladores;

import com.api.rest.cuadre.atmrefactor.entidades.ErrorResponse;
import com.api.rest.cuadre.atmrefactor.entidades.RequestDate;
import com.api.rest.cuadre.atmrefactor.entidades.TrxDiarias;
import com.api.rest.cuadre.atmrefactor.servicios.TrxServicio;
import com.api.rest.cuadre.utils.Utilerias;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/rpa_trx_diaria_atm")
@Slf4j
public class TrxControlador {

    private final TrxServicio servicio;
    private final Utilerias utilerias;
    private static final String SEPARADOR = "*********************************************************************************************************";

    public TrxControlador(TrxServicio servicio, Utilerias utilerias) {
        this.servicio = servicio;
        this.utilerias = utilerias;
    }

    @Operation(summary = "clientesPorTrx", description = "Metodo para consulta de Transacciones Diarias")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Solicitud Aceptada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "409", description = "Error 409"),
            @ApiResponse(responseCode = "500", description = "internal server error")})
    @PostMapping("/verid")
    public CompletableFuture<ResponseEntity<?>> trx(@RequestBody RequestDate request) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        log.info(SEPARADOR);
        log.info("* METODO2: trxDiarias - F.Consumo: {} F.Desde: {} F.Hasta: {}", utilerias.fechaHora(), request.getGdFechaDesde(), request.getGdFechaHasta());

        if (utilerias.esFechaValida(request.getGdFechaDesde()) || utilerias.esFechaValida(request.getGdFechaHasta())) {
            log.error("Fecha invalida proporcionada.");
            log.info(SEPARADOR);
            ErrorResponse errorResponse = new ErrorResponse(
                    LocalDateTime.now().toString(),
                    400,
                    "Bad Request",
                    "/CuadreATM/rpa_trx_diaria_atm/verid"
            );
            return CompletableFuture.completedFuture(new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST));
        }

        CompletableFuture<List<TrxDiarias>> listaTrxFuture = servicio.listadoTrx(request.getGdFechaDesde(), request.getGdFechaHasta());
        return listaTrxFuture.thenApply(listaTrx -> {
            if (listaTrx == null || listaTrx.isEmpty()) {
                log.warn("No hay data para trxDiarias F.Consumo: {} F.Desde: {} F.Hasta: {}", utilerias.fechaHora(), request.getGdFechaDesde(), request.getGdFechaHasta());
                log.info(SEPARADOR);
                return new ResponseEntity<>("[]", HttpStatus.BAD_REQUEST);
            }
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            log.info("Tiempo de ejecucion METODO: trxDiarias: {}ms", duration);
            log.info(SEPARADOR);
            return new ResponseEntity<>(listaTrx, HttpStatus.OK);
        }).exceptionally(ex -> {
            log.error("Error al obtener las trxDiarias: {}", ex.getMessage());
            return new ResponseEntity<>("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
        });
    }
}