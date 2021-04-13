package com.isabel.aa_api_datos.repository;

import com.isabel.aa_api_datos.domain.Cancion;
import com.isabel.aa_api_datos.domain.Cantante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CancionRepository extends CrudRepository<Cancion, Integer> {
//
//    Set<Cancion> findAll();
//    Set<Cancion> findByCantante(Cantante cantante);
}
