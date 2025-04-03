package com.api.rest.cuadre.atmrefactor.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.cuadre.atmrefactor.entidades.Clientes;
import com.api.rest.cuadre.atmrefactor.entidades.RequestDate;
import com.api.rest.cuadre.atmrefactor.servicios.ClienteServicios;
import com.api.rest.cuadre.utils.Utilerias;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/clienteTrx")
@Slf4j
public class ClienteControlador {

    private final ClienteServicios clienteServicios;
    private final Utilerias utilerias;

    private static final String SEPARADOR = "**********************************************************************************";

    public ClienteControlador(ClienteServicios clienteServicios, Utilerias utilerias) {
        this.clienteServicios = clienteServicios;
        this.utilerias = utilerias;
    }

    @Operation(summary = "clientesPorTrx", description = "Metodo para consulta de Cliente Transacciones")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Solicitud Aceptada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "409", description = "Error 409"),
            @ApiResponse(responseCode = "500", description = "internal server error")})
    @PostMapping("/verid")
    public ResponseEntity<Object> getTrxClientes(@RequestBody RequestDate request) {
        long startTime = System.currentTimeMillis();
        log.info(SEPARADOR);
        log.info("* METODO: getTrxClientes - F.Consumo: {} F.Desde: {} - F.Hasta: {}", utilerias.fechaHora(), request.getGdFechaDesde(), request.getGdFechaHasta());

        if (!utilerias.esFechaValida(request.getGdFechaDesde()) || !utilerias.esFechaValida(request.getGdFechaHasta())) {
            log.error("Fecha invalida proporcionada.");
            log.info(SEPARADOR);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fecha inválida proporcionada.");
        }

        List<Clientes> clientesTrxs = clienteServicios.listaTrxClientes(request.getGdFechaDesde(),
                request.getGdFechaHasta());

        if (clientesTrxs.isEmpty()) {
            log.info("No hay data para getTrxClientes - F.Consumo: {} F.Desde: {} - F.Hasta: {}", utilerias.fechaHora(), request.getGdFechaDesde(), request.getGdFechaHasta());
			log.info(SEPARADOR);
            return new ResponseEntity<>("No hay datos para los parámetros ingresados", HttpStatus.NO_CONTENT);
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        log.info("Tiempo de ejecución getTrxClientes: {}ms", duration);
        log.info(SEPARADOR);
        return new ResponseEntity<>(clientesTrxs, HttpStatus.OK);
    }

}
