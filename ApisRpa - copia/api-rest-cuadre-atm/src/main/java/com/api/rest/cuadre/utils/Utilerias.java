package com.api.rest.cuadre.utils;

import com.api.rest.cuadre.atmrefactor.entidades.RequestDate;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Component
public class Utilerias {
	public String fechaHora() {
		LocalDateTime fechaHora = LocalDateTime.now();
		return String.valueOf(fechaHora);
	}

	public boolean esFechaValida(String fecha) {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
			formato.setLenient(false);
			formato.parse(fecha);
			return false;
		} catch (ParseException e) {
			return true;
		}
	}

	public boolean fechasInvalidas(RequestDate request) {
		return esFechaValida(request.getGdFechaDesde()) || esFechaValida(request.getGdFechaHasta());
	}
}
