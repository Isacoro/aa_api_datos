package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Cancion;
import com.isabel.aa_api_datos.exception.CancionNotFoundException;
import com.isabel.aa_api_datos.repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
    public Optional<Cancion> findCancionById(long id) {
        return cancionRepository.findById(id);
    }

    @Override
    public Cancion addCancion(Cancion cancion) {
        return cancionRepository.save(cancion);
    }

    @Override
    public Cancion modifyCancion(long id, Cancion nuevaCancion) {
        Cancion cancion = cancionRepository.findById(id)
                .orElseThrow(() -> new CancionNotFoundException(id));
        nuevaCancion.setId(cancion.getId());
        return cancionRepository.save(nuevaCancion);
    }

    @Override
    public void deleteCancion(long id) {
        cancionRepository.findById(id)
                .orElseThrow(() -> new CancionNotFoundException(id));
        cancionRepository.deleteById(id);

    }
}
