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
@Entity(name = "Cantante")
public class Cantante {

    @Schema(description = "Identificador del cantante", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Schema(description = "Nombre del cantante", example = "Shakira", required = true)
    @NotBlank
    @Column
    private String nombre;
    @Schema(description = "Nacionalidad del cantante", example = "Español")
    @Column
    private String nacionalidad;
    @Schema(description = "Cantante en activo", example = "false")
    @Column
    private boolean activo;
    @Schema(description = "Fecha de nacimiento del cantante", example = "1982-03-22")
    @Column(name = "fecha_nacimiento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    @Schema(description = "Años en activo del cantante", example = "15")
    @Column(name = "anos_activo")
    private float anosActivo;

    //1 Cantante tiene N discos
    @OneToMany(mappedBy = "cantante")
    @JsonManagedReference
    private List<Disco> discos;

    //1 Cantante N Conciertos
    @OneToMany(mappedBy = "cantante")
    @JsonManagedReference
    private List<Concierto> conciertos;
}
