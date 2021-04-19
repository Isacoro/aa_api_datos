package com.isabel.aa_api_datos.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Concierto")
public class Concierto {

    @Schema(description = "Identificador del concierto", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Schema(description = "Localidad donde se celebra", example = "Zaragoza")
    @Column
    private String localidad;
    @Schema(description = "Ya se ha celebrado o no", example = "true")
    @Column
    private boolean celebrado;
    @Schema(description = "Fecha del concierto", example = "12-06-2021")
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha;
    @Schema(description = "Precio de la entrada", example = "35.95")
    @Column
    private float precio;
    @Schema(description = "Recinto donde se celebra el concierto", example = "Estadio La Romareda")
    @Column
    private String recinto;
    @Schema(description = "Aforo del recinto", example = "35000")
    @Column
    private long aforo;

    //N Conciertos 1 Cantante
    @ManyToOne
    @JoinColumn(name = "cantante_id")
    @JsonBackReference(value = "cantante")
    private Cantante cantante;
}
