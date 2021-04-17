package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Disco;

import java.util.Optional;
import java.util.Set;

public interface DiscoService {

    //Consultar
    Set<Disco> findAll();

    Optional<Disco> findDiscoById(long id);

    //Crear
    Disco addDisco(Disco disco);

    //Modificar
    Disco modifyDisco(long id, Disco nuevoDisco);

    //Borrar
    void deleteDisco(long id);
}
