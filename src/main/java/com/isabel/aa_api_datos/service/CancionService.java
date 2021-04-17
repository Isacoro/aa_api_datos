package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Cancion;

import java.util.Optional;
import java.util.Set;

public interface CancionService {

    //Consultar
    Set<Cancion> findAll();

    Optional<Cancion> findCancionById(long id);

    //Crear
    Cancion addCancion(Cancion cancion);

    //Modificar
    Cancion modifyCancion(long id, Cancion nuevaCancion);

    //Borrar
    void deleteCancion(long id);

}
