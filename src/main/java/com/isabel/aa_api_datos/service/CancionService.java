package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Cancion;
import com.isabel.aa_api_datos.domain.Cantante;

import java.util.Set;

public interface CancionService {

    //Consultar
    Set<Cancion> findAll();
    Cancion findById(int id);

    //Crear
    Cancion nuevaCancion(Cancion cancion);

    //Modificar
    Cancion modificarCancion(int id, Cancion nuevaCancion);

    //Borrar
    void borrarCancion(int id);

}
