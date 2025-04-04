package com.api.rest.cuadre.atmrefactor.servicios;

import com.api.rest.cuadre.atmrefactor.entidades.ResumenTrx;
import com.api.rest.cuadre.atmrefactor.repositorios.ResumenTrxRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumenTrxServicio {

    private final ResumenTrxRepositorio resumenTrxRepositorio;

    public ResumenTrxServicio(ResumenTrxRepositorio resumenTrxRepositorio) {
        this.resumenTrxRepositorio = resumenTrxRepositorio;
    }

    public List<ResumenTrx> listaResumen(String gdFechaDesde, String gdFechaHasta) {
		return resumenTrxRepositorio.lista(gdFechaDesde, gdFechaHasta);
    }

}
