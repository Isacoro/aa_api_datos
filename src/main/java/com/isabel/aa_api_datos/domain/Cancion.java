package com.isabel.aa_api_datos.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Cancion")
public class Cancion {

    @Schema(description = "Identificador de la canción", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Schema(description = "Título de la canción", example = "Chantaje", required = true)
    @NotBlank
    @Column
    private String titulo;
    @Schema(description = "Si ha estado en el top10", example = "true")
    @Column
    private boolean top10;
    @Schema(description = "Fecha de lanzamiento", example = "2020-05-17")
    @Column(name = "fecha_salida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaSalida;
    @Schema(description = "Número de descargas", example = "120300")
    @Column(name = "numero_descargas")
    private long numDescargas;
    @Schema(description = "Tiempo de duración de la canción", example = "4.25")
    @Column
    private float duracion;

    //N Canciones 1 Disco
    @ManyToOne
    @JoinColumn(name = "disco_id")
    @JsonBackReference
    private Disco disco;

}
