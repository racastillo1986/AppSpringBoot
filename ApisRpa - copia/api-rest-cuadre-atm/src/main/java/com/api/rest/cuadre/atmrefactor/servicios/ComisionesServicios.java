package com.api.rest.cuadre.atmrefactor.servicios;

import com.api.rest.cuadre.atmrefactor.entidades.Comisiones;
import com.api.rest.cuadre.atmrefactor.repositorios.ComisionesRepositorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class ComisionesServicios {
	
	private final ComisionesRepositorio comisionesRepositorio;
	
	public ComisionesServicios(ComisionesRepositorio comisionesRepositorio) {
		this.comisionesRepositorio = comisionesRepositorio;
	}

	@Async
	public CompletableFuture<List<Comisiones>> listaComisiones(String gdFechaDesde, String gdFechaHasta) throws Exception {
		List<Comisiones> listaComisiones = comisionesRepositorio.listaComisionesR(gdFechaDesde, gdFechaHasta);
		return CompletableFuture.completedFuture(listaComisiones);
	}
}
