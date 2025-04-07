package com.api.rest.cuadre.atmrefactor.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Comisiones {

    private Long id;

    @JsonProperty("numero_cuenta")
    private Long numeroCuenta;

    @JsonProperty("secuencia_trx_cms")
    private int secuenciaTrxCms;

    @JsonProperty("fecha_valida")
    private Date fechaValida;

    private String hora;

    @JsonProperty("codigo_tipo_transaccion")
    private int codigoTipoTransaccion;

    private String descripcion;

    @JsonProperty("clase_cuenta")
    private String claseCuenta;

    @JsonProperty("clase_de_cuenta")
    private int claseDeCuenta;

    private Float valor;

    @JsonProperty("codigo_agencia")
    private int codigoAgencia;

    @JsonProperty("nombre_agencia")
    private String nombreAgencia;

    @JsonProperty("numero_tarjeta")
    private String numeroTarjeta;

    @JsonProperty("tipo_transaccion")
    private String tipoTransaccion;

    @JsonProperty("codigo_canal_adquiriente")
    private String codigoCanalAdquiriente;
}
