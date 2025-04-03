package com.api.rest.cuadre.atmrefactor.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "rpa_seed_valores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Seed {
	
	@Id
	private Long id;

	@Column(name = "numero_identificacion")
	@JsonProperty("numero_identificacion")
	private String numeroIdentificacion;

	@Column(name = "tipo_comprobante")
	@JsonProperty("tipo_comprobante")
	private int tipoComprobante;

	@Column(name = "num_documento")
	@JsonProperty("num_documento")
	private String numDocumento;

	@Column(name = "establecimiento")
	private int establecimiento;

	@Column(name = "punto_emision")
	@JsonProperty("punto_emision")
	private int puntoEmision;

	@Column(name = "numero_comprobante")
	@JsonProperty("numero_comprobante")
	private Long numeroComprobante;

	@Column(name = "valor_total")
	@JsonProperty("valor_total")
	private Float valorTotal;

	@Column(name = "numero_factura")
	private String numeroFactura;

}
