package com.api.rest.cuadre.atmrefactor.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.rest.cuadre.atmrefactor.entidades.Seed;
import com.api.rest.cuadre.atmrefactor.repositorios.SeedRepositorio;

@Service
public class SeedServicio {
	
	private final SeedRepositorio seedRepositorio;
	
	public SeedServicio(SeedRepositorio seedRepositorio) {
		this.seedRepositorio = seedRepositorio;
	}
	
	public List<Seed> listaSeed(String gvIdentificacion, String gdFechaDesde, String gdFechaHasta){
		seedRepositorio.listaProcedure(gvIdentificacion, gdFechaDesde, gdFechaHasta);
		return seedRepositorio.findAll();
	}
}
