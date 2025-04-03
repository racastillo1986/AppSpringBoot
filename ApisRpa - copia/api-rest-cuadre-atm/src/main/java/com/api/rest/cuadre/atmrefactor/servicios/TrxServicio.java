package com.api.rest.cuadre.atmrefactor.servicios;

import com.api.rest.cuadre.atmrefactor.entidades.TrxDiarias;
import com.api.rest.cuadre.atmrefactor.repositorios.TrxRepositorio;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TrxServicio {

    private final TrxRepositorio repositorio;

    public TrxServicio(TrxRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Async
    public CompletableFuture<List<TrxDiarias>> listadoTrx(String fechaDesde, String fechaHasta) throws Exception {
        List<TrxDiarias> listaTrx = repositorio.listaTrx(fechaDesde, fechaHasta);
        return CompletableFuture.completedFuture(listaTrx);
    }

}
