package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Cancion;
import com.isabel.aa_api_datos.domain.Concierto;

import java.util.Set;

public interface ConciertoService {

    //Consultar
    Set<Concierto> findAll();
    Concierto findById(int id);

    //Crear
    Concierto nuevoConcierto(Concierto concierto);

    //Modificar
    Concierto modificarConcierto(int id, Concierto nuevoConcierto);

    //Borrar
    void borrarConcierto(int id);
}
