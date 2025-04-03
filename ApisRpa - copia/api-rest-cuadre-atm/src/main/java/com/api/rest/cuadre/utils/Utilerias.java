package com.api.rest.cuadre.utils;

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
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}
