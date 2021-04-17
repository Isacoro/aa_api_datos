package com.isabel.aa_api_datos.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CantanteDTO {

    @Schema(description = "Nombre del cantante", example = "Shakira", required = true)
    private String nombre;
    @Schema(description = "Nacionalidad del cantante", example = "Español")
    private String nacionalidad;
    @Schema(description = "Cantante en activo", example = "false")
    private boolean activo;
    @Schema(description = "Fecha de nacimiento del cantante", example = "1982-03-22")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    @Schema(description = "Años en activo del cantante", example = "15")
    private float anosActivo;
}
