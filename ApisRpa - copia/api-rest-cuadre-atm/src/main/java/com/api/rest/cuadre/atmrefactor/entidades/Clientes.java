package com.api.rest.cuadre.atmrefactor.entidades;

import java.util.Date;

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
@Table(name = "rpa_clientes_x_trx")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Clientes {
	@Id
	private Long id;
	
	@Column(name = "codigo_usuario")
	@JsonProperty("codigo_usuario")
	private String codigoUsuario;
	
	@Column(name = "numero_cuenta")
	@JsonProperty("numero_cuenta")
	private Long numeroCuenta;
	
	@Column(name = "numero_identificacion")
	@JsonProperty("numero_identificacion")
	private String numeroIdentificacion;
	
	@Column(name = "fecha_valida")
	@JsonProperty("fecha_valida")
	private Date  fechaValida;
	
	@Column(name = "codigo_tipo_transaccion")
	@JsonProperty("codigo_tipo_transaccion")
	private int codigoTipoTransaccion;
	
	@Column(name = "codigo_agencia")
	@JsonProperty("codigo_agencia")
	private int codigoAgencia;
	
	@Column(name = "nombre_agencia")
	@JsonProperty("nombre_agencia")
	private String nombreAgencia;
	
	@Column(name = "base_imponible")
	@JsonProperty("base_imponible")
	private Float baseImponible;
	
	@Column(name = "secuencia_movimiento_origen")
	@JsonProperty("secuencia_movimiento_origen")
	private Long secuenciaMovimientoOrigen;
	
	@Column(name = "codigo_aplicacion")
	@JsonProperty("codigo_aplicacion")
	private String codigoAplicacion;
	
	@Column(name = "codigo_subaplicacion")
	@JsonProperty("codigo_subaplicacion")
	private int codigoSubAplicacion;
	
	@Column(name = "iva")
	private Float iva;
	
	@Column(name = "valor_total")
	@JsonProperty("valor_total")
	private Float valorTotal;
	
	@Column(name = "valor_sin_impuestos")
	@JsonProperty("valor_sin_impuestos")
	private Float valorSinImpuestos;
}
