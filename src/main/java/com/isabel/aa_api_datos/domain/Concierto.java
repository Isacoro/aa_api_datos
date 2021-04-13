package com.isabel.aa_api_datos.domain;


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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String localidad;
    @Column
    private boolean celebrado;
    @Column
    private LocalDate fecha;
    @Column
    private float precio;
    @Column
    private int aforo;

    //N Conciertos 1 Cantante
    @ManyToOne
    @JoinColumn(name = "cantante_id")
    private Cantante cantante;
}