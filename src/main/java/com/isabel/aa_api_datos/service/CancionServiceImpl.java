package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Cancion;
import com.isabel.aa_api_datos.domain.Disco;
import com.isabel.aa_api_datos.domain.dto.CancionDTO;
import com.isabel.aa_api_datos.exception.CancionNotFoundException;
import com.isabel.aa_api_datos.exception.DiscoNotFoundException;
import com.isabel.aa_api_datos.repository.CancionRepository;
import com.isabel.aa_api_datos.repository.DiscoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CancionServiceImpl implements CancionService{

    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private DiscoRepository discoRepository;



    @Override
    public Set<Cancion> findAll() {
        return cancionRepository.findAll();
    }

    @Override
    public Optional<Cancion> findCancionById(long id) {
        return cancionRepository.findById(id);
    }

    @Override
    public Cancion addCancion(CancionDTO cancionDTO) {
        Disco disco = discoRepository.findById(cancionDTO.getDisco_id())
                .orElseThrow(() -> new DiscoNotFoundException("Disco no encontrado"));
        Cancion nuevaCancion = new Cancion();
        nuevaCancion.setTitulo(cancionDTO.getTitulo());
        nuevaCancion.setDuracion(cancionDTO.getDuracion());
        nuevaCancion.setDisco(disco);
        return cancionRepository.save(nuevaCancion);
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
