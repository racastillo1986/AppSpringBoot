package com.api.rest.cuadre.atmrefactor.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String timestamp;
    private int status;
    private String error;
    private String path;
}