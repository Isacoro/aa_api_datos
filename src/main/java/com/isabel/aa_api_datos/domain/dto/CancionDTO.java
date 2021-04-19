package com.isabel.aa_api_datos.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CancionDTO {

    private String titulo;
    private float duracion;
    private long disco_id;
}
