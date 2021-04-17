package com.isabel.aa_api_datos.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ConciertoDTO {

    private String localidad;
    private boolean celebrado;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    private float precio;
    private String recinto;
    private long aforo;
    private long cantante_id;
}
