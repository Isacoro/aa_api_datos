package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Cancion;
import com.isabel.aa_api_datos.domain.Cantante;
import com.isabel.aa_api_datos.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CancionServiceImpl implements CancionService{

    @Autowired
    private CancionRepository cancionRepository;


    @Override
    public Set<Cancion> findAll() {
        return cancionRepository.findAll();
    }

    @Override
    public Cancion findById(int id) {
        return cancionRepository.findById(id);
    }

    @Override
    public Cancion nuevaCancion(Cancion cancion) {
        return cancionRepository.save(cancion);
    }

    @Override
    public Cancion modificarCancion(int id, Cancion nuevaCancion) {
        Cancion cancion = cancionRepository.findById(id);
        nuevaCancion.setId(cancion.getId());
        return cancionRepository.save(nuevaCancion);
    }

    @Override
    public void borrarCancion(int id) {
        cancionRepository.findById(id);
        cancionRepository.deleteById(id);

    }
}
