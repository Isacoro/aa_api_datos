package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Concierto;
import com.isabel.aa_api_datos.repository.ConciertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class ConciertoServiceImpl implements ConciertoService{

    @Autowired
    private ConciertoRepository conciertoRepository;



    @Override
    public Set<Concierto> findAll() {
        return conciertoRepository.findAll();
    }

    @Override
    public Concierto findById(int id) {
        return conciertoRepository.findById(id);
    }

    @Override
    public Concierto nuevoConcierto(Concierto concierto) {
        return conciertoRepository.save(concierto);
    }

    @Override
    public Concierto modificarConcierto(int id, Concierto nuevoConcierto) {
        Concierto concierto = conciertoRepository.findById(id);
        nuevoConcierto.setId(concierto.getId());
        return conciertoRepository.save(nuevoConcierto);
    }

    @Override
    public void borrarConcierto(int id) {
        conciertoRepository.findById(id);
        conciertoRepository.deleteById(id);

    }
}
