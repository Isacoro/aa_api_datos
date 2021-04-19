package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Cantante;
import com.isabel.aa_api_datos.exception.CantanteNotFoundException;
import com.isabel.aa_api_datos.repository.CantanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CantanteServiceImpl implements CantanteService {

    @Autowired
    private CantanteRepository cantanteRepository;

    //Buscar todos
    @Override
    public Set<Cantante> findAll() {
        return cantanteRepository.findAll();
    }

    //Búsqueda id
    @Override
    public Optional<Cantante> findCantanteById(long id) {
        return cantanteRepository.findById(id);
    }

//    //Búsqueda por nombre
//    @Override
//    public Cantante findByName(String name) {
//        return cantanteRepository.findByName(name);
//    }


    //Crear
    @Override
    public Cantante addCantante(Cantante cantante) {
        return cantanteRepository.save(cantante);
    }

    //Modificar
    @Override
    public Cantante modifyCantante(long id, Cantante nuevoCantante) {
        Cantante cantante = cantanteRepository.findById(id)
                .orElseThrow(() -> new CantanteNotFoundException(id));
        nuevoCantante.setId(cantante.getId());
        return cantanteRepository.save(nuevoCantante);
    }

    //Borrar
    @Override
    public void deleteCantante(long id) {
        cantanteRepository.findById(id)
                .orElseThrow(() -> new CantanteNotFoundException(id));
        cantanteRepository.deleteById(id);

    }
}
