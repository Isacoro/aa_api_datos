package com.isabel.aa_api_datos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Cantante")
public class Cantante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column
    private String nacionalidad;
    @Column
    private boolean activo;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @Column(name = "anos_activo")
    private float anosActivo;

    //1 Cantante tiene N discos
    @OneToMany(mappedBy = "cantante")
    private List<Disco> ListaDiscos;

    //1 Cantante N Conciertos
    @OneToMany(mappedBy = "cantante")
    private List<Concierto> ListaConciertos;





}
