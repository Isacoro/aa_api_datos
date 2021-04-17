package com.isabel.aa_api_datos.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DiscoDTO {

    private String nombre;
    private boolean premiado;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaSalida;
    private float precio;
    private long numVentas;
}
