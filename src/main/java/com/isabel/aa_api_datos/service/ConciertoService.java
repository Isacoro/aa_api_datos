package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Concierto;
import com.isabel.aa_api_datos.domain.dto.ConciertoDTO;

import java.util.Optional;
import java.util.Set;

public interface ConciertoService {

    //Consultar
    Set<Concierto> findAll();

    Optional<Concierto> findConciertoById(long id);

    Set<Concierto> findByLocalidadAndCelebradoAndPrecio(String localidad, boolean celebrado, float precio);

    //Crear
    Concierto addConcierto(ConciertoDTO conciertoDTO);

    //Modificar
    Concierto modifyConcierto(long id, Concierto nuevoConcierto);

    //Borrar
    void deleteConcierto(long id);
}
