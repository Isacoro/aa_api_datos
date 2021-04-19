package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Disco;
import com.isabel.aa_api_datos.domain.dto.DiscoDTO;

import java.util.Optional;
import java.util.Set;

public interface DiscoService {

    //Consultar
    Set<Disco> findAll();

    Optional<Disco> findDiscoById(long id);

    //Crear
    Disco addDisco(DiscoDTO discoDTO);

    //Modificar
    Disco modifyDisco(long id, Disco nuevoDisco);

    //Borrar
    void deleteDisco(long id);
}
