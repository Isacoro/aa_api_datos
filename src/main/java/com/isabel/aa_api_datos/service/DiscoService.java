package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Cancion;
import com.isabel.aa_api_datos.domain.Disco;

import java.util.Set;

public interface DiscoService {

    //Consultar
    Set<Disco> findAll();
    Disco findById(int id);

    //Crear
    Disco nuevoDisco(Disco disco);

    //Modificar
    Disco modificarDisco(int id, Disco nuevoDisco);

    //Borrar
    void borrarDisco(int id);
}
