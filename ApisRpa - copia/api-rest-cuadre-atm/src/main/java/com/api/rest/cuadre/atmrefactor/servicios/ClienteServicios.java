package com.api.rest.cuadre.atmrefactor.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.rest.cuadre.atmrefactor.entidades.Clientes;
import com.api.rest.cuadre.atmrefactor.repositorios.ClienteRepositorio;

@Service
public class ClienteServicios {
	
	private final ClienteRepositorio clienteRepositorio;
	
	public ClienteServicios(ClienteRepositorio clienteRepositorio) {
		this.clienteRepositorio = clienteRepositorio;
	}
	
	public List<Clientes> listaTrxClientes(String gdFechaDesde, String gdFechaHasta) {
		clienteRepositorio.listaClientes(gdFechaDesde, gdFechaHasta);
		return clienteRepositorio.findAll();
	}

}
