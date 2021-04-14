package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Cantante;
import com.isabel.aa_api_datos.repository.CantanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CantanteServiceImpl implements CantanteService {

    @Autowired
    private CantanteRepository cantanteRepository;



    @Override
    public Set<Cantante> findAll() {
        return cantanteRepository.findAll();
    }

    @Override
    public Cantante findById(int id) {
        return cantanteRepository.findById(id);
    }

    @Override
    public Cantante nuevoCantante(Cantante cantante) {
        return cantanteRepository.save(cantante);
    }

    @Override
    public Cantante modificarCantante(int id, Cantante nuevoCantante) {
        Cantante cantante = cantanteRepository.findById(id);
        nuevoCantante.setId(cantante.getId());
        return cantanteRepository.save(nuevoCantante);
    }

    @Override
    public void borrarCantante(int id) {
        cantanteRepository.findById(id);
        cantanteRepository.deleteById(id);

    }
}
