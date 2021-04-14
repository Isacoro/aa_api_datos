package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Cancion;
import com.isabel.aa_api_datos.domain.Cantante;

import java.util.Set;

public interface CantanteService {

    //Consultar
    Set<Cantante> findAll();
    Cantante findById (int id);

    //Crear
    Cantante nuevoCantante(Cantante cantante);

    //Modificar
    Cantante modificarCantante(int id, Cantante nuevoCantante);

    //Borrar
    void borrarCantante(int id);
}
