package com.isabel.aa_api_datos.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Disco")
public class Disco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column
    private boolean premiado;
    @Column(name ="fecha_salida")
    private LocalDate fechaSalida;
    @Column
    private float precio;
    @Column(name = "numero_ventas")
    private int numVentas;

    //N Discos 1 Cantante
    @ManyToOne
    @JoinColumn(name = "cantante_id")
    private Cantante cantante;

    //1 Disco N Canciones
    @OneToMany(mappedBy = "disco")
    private List<Cancion> listaCanciones;


}
