package com.isabel.aa_api_datos.repository;

import com.isabel.aa_api_datos.domain.Cancion;
import com.isabel.aa_api_datos.domain.Disco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DiscoRepository extends CrudRepository<Disco, Integer> {

    Set<Disco> findAll();

    Disco findById(int id);
}
