package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Concierto;

import java.util.Optional;
import java.util.Set;

public interface ConciertoService {

    //Consultar
    Set<Concierto> findAll();

    Optional<Concierto> findConciertoById(long id);

    //Crear
    Concierto addConcierto(Concierto concierto);

    //Modificar
    Concierto modifyConcierto(long id, Concierto nuevoConcierto);

    //Borrar
    void deleteConcierto(long id);
}
