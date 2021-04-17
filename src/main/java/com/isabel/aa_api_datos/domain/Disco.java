package com.isabel.aa_api_datos.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Disco")
public class Disco {

    @Schema(description = "Identificador del disco", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Schema(description = "Título del disco", example = "Más", required = true)
    @NotBlank
    @Column
    private String nombre;
    @Schema(description = "Disco premiado o no", example = "true")
    @Column
    private boolean premiado;
    @Schema(description = "Fecha que sale a la venta", example = "1986-10-09")
    @Column(name ="fecha_salida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaSalida;
    @Schema(description = "Precio de venta", example = "30.99")
    @Column
    private float precio;
    @Schema(description = "Número de ventas", example = "150000")
    @Column(name = "numero_ventas")
    private long numVentas;

    //N Discos 1 Cantante
    @ManyToOne
    @JoinColumn(name = "cantante_id")
    @JsonBackReference
    private Cantante cantante;

    //1 Disco N Canciones
    @OneToMany(mappedBy = "disco")
    @JsonManagedReference
    private List<Cancion> canciones;


}
