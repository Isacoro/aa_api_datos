package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Cantante;

import java.util.Optional;
import java.util.Set;

public interface CantanteService {

    //Consultar
    Set<Cantante> findAll();
    Optional<Cantante> findCantanteById(long id);
    Set<Cantante> findByNombreAndNacionalidadAndActivo(String nombre, String nacionalidad, boolean activo);

    //Crear
    Cantante addCantante(Cantante cantante);

    //Modificar
    Cantante modifyCantante(long id, Cantante nuevoCantante);

    //Borrar
    void deleteCantante(long id);
}
