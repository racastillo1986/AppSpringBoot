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
@Table(name = "rpa_trx_diarias_atm")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TrxDiarias {
	
	@Id
    private Long id;

    @Column(name = "numero_cuenta")
    @JsonProperty("numero_cuenta")
    private Long numeroCuenta;

    @Column(name = "secuencia_trx_cms")
    @JsonProperty("secuencia_trx_cms")
    private int secuenciaTrxCms;

    @Column(name = "fecha_valida")
    @JsonProperty("fecha_valida")
    private Date fechaValida;

    @Column(name = "hora")
    private String hora;

    @Column(name = "codigo_tipo_transaccion")
    @JsonProperty("codigo_tipo_transaccion")
    private int codigoTipoTransaccion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "clase_cuenta")
    @JsonProperty("clase_cuenta")
    private String claseCuenta;

    @Column(name = "clase_de_cuenta")
    @JsonProperty("clase_de_cuenta")
    private int claseDeCuenta;

    @Column(name = "desc_clase_cuenta")
    @JsonProperty("desc_clase_cuenta")
    private String descClaseCuenta;

    @Column(name = "valor")
    private Float valor;

    @Column(name = "codigo_agencia")
    @JsonProperty("codigo_agencia")
    private int codigoAgencia;

    @Column(name = "nombre_agencia")
    @JsonProperty("nombre_agencia")
    private String nombreAgencia;

    @Column(name = "numero_tarjeta")
    @JsonProperty("numero_tarjeta")
    private String numeroTarjeta;

    @Column(name = "tipo_transaccion")
    @JsonProperty("tipo_transaccion")
    private String tipoTransaccion;

    @Column(name = "codigo_canal_adquiriente")
    @JsonProperty("codigo_canal_adquiriente")
    private String codigoCanalAdquiriente;

}
