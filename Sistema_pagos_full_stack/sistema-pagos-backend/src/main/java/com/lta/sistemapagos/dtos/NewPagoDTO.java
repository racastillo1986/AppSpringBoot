package com.lta.sistemapagos.dtos;

import java.time.LocalDate;

import com.lta.sistemapagos.enums.TypePago;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPagoDTO {
    private double cantidad;
    private TypePago typePago;
    private LocalDate date;
    private String codigoEstudiante;
}
