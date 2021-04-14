package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Disco;
import com.isabel.aa_api_datos.repository.DiscoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DiscoServiceImpl implements DiscoService {

    @Autowired
    private DiscoRepository discoRepository;



    @Override
    public Set<Disco> findAll() {
        return discoRepository.findAll();
    }

    @Override
    public Disco findById(int id) {
        return discoRepository.findById(id);
    }

    @Override
    public Disco nuevoDisco(Disco disco) {
        return discoRepository.save(disco);
    }

    @Override
    public Disco modificarDisco(int id, Disco nuevoDisco) {
        Disco disco = discoRepository.findById(id);
        nuevoDisco.setId(disco.getId());
        return discoRepository.save(nuevoDisco);
    }

    @Override
    public void borrarDisco(int id) {
        discoRepository.findById(id);
        discoRepository.deleteById(id);
    }
}
