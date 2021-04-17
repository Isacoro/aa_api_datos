package com.isabel.aa_api_datos.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CancionDTO {

    private String titulo;
    private boolean top10;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaSalida;
    private long numDescargas;
    private long disco_id;
}
