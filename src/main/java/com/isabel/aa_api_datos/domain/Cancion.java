package com.isabel.aa_api_datos.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Cancion")
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String titulo;
    @Column
    private boolean top10;
    @Column(name = "fecha_salida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaSalida;
    @Column(name = "numero_descargas")
    private int numDescargas;
    @Column
    private float duracion;

    //N Canciones 1 Disco
    @ManyToOne
    @JoinColumn(name = "disco_id")
    private Disco disco;

}
