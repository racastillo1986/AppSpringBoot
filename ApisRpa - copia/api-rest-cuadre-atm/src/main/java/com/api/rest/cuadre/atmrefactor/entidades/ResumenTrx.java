package com.api.rest.cuadre.atmrefactor.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ResumenTrx {

	private Long id;

    @JsonProperty("codigo_agencia")
    private int codigoAgencia;

    @JsonProperty("codigo_clase_cuenta")
    private int codigoClaseCuenta;

    @JsonProperty("descripcion_clase")
    private String descripcionClase;

    @JsonProperty("codigo_tipo_transaccion")
    private int codigoTipoTransaccion;

    private String descripcion;

    private Float valor;

    private int cantidad;

}
