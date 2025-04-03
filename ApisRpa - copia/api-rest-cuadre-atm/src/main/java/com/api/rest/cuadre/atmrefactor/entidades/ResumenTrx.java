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
@Table(name = "rpa_resumen_trx_atm")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ResumenTrx {
	
	@Id
    private Long id;

    @Column(name = "codigo_agencia")
    @JsonProperty("codigo_agencia")
    private int codigoAgencia;

    @Column(name = "codigo_clase_cuenta")
    @JsonProperty("codigo_clase_cuenta")
    private int codigoClaseCuenta;

    @Column(name = "descripcion_clase")
    @JsonProperty("descripcion_clase")
    private String descripcionClase;

    @Column(name = "codigo_tipo_transaccion")
    @JsonProperty("codigo_tipo_transaccion")
    private int codigoTipoTransaccion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "valor")
    private Float valor;

    @Column(name = "cantidad")
    private int cantidad;

}
