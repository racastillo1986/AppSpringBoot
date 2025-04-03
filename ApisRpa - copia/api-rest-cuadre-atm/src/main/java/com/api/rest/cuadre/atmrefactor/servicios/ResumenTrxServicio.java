package com.api.rest.cuadre.atmrefactor.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.rest.cuadre.atmrefactor.entidades.ResumenTrx;
import com.api.rest.cuadre.atmrefactor.repositorios.ResumenTrxRepositorio;

@Service
public class ResumenTrxServicio {

    private final ResumenTrxRepositorio resumenTrxRepositorio;

    public ResumenTrxServicio(ResumenTrxRepositorio resumenTrxRepositorio) {
        this.resumenTrxRepositorio = resumenTrxRepositorio;
    }

    public List<ResumenTrx> listaResumen(String gdFechaDesde, String gdFechaHasta) /*throws Exception */{

		return resumenTrxRepositorio.lista(gdFechaDesde, gdFechaHasta);
/*
		resumenTrxRepositorio.listaProcedure(gdFechaDesde, gdFechaHasta);		
        return resumenTrxRepositorio.findAll();
*/
    }

}
