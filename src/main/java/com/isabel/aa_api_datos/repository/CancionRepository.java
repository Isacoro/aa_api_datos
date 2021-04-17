package com.isabel.aa_api_datos.repository;

import com.isabel.aa_api_datos.domain.Cancion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CancionRepository extends CrudRepository<Cancion, Long> {

    Set<Cancion> findAll();
}
