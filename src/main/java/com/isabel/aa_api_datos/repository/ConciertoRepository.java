package com.isabel.aa_api_datos.repository;

import com.isabel.aa_api_datos.domain.Cancion;
import com.isabel.aa_api_datos.domain.Concierto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ConciertoRepository extends CrudRepository<Concierto, Integer> {

//    Set<Concierto> findAll();
}
