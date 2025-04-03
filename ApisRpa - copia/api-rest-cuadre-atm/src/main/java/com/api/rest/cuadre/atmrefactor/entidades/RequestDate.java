package com.api.rest.cuadre.atmrefactor.entidades;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RequestDate implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("gd_fecha_desde")
	private String gdFechaDesde;
	
	@JsonProperty("gd_fecha_hasta")
	private String gdFechaHasta;
	
	@JsonProperty("gv_identificacion")
	private String gvIdentificacion;

}
