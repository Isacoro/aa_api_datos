package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Cantante;
import com.isabel.aa_api_datos.domain.Concierto;
import com.isabel.aa_api_datos.domain.dto.ConciertoDTO;
import com.isabel.aa_api_datos.exception.CantanteNotFoundException;
import com.isabel.aa_api_datos.exception.ConciertoNotFoundException;
import com.isabel.aa_api_datos.repository.CantanteRepository;
import com.isabel.aa_api_datos.repository.ConciertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public class ConciertoServiceImpl implements ConciertoService{

    @Autowired
    private ConciertoRepository conciertoRepository;

    @Autowired
    private CantanteRepository cantanteRepository;



    @Override
    public Set<Concierto> findAll() {
        return conciertoRepository.findAll();
    }

    @Override
    public Optional<Concierto> findConciertoById(long id) {
        return conciertoRepository.findById(id);
    }

    @Override
    public Concierto addConcierto(ConciertoDTO conciertoDTO) {
        Cantante cantante = cantanteRepository.findById(conciertoDTO.getCantante_id())
                .orElseThrow(() -> new CantanteNotFoundException("Cantante no encontrado"));
        Concierto nuevoConcierto = new Concierto();
        nuevoConcierto.setLocalidad(conciertoDTO.getLocalidad());
        nuevoConcierto.setAforo(conciertoDTO.getAforo());
        nuevoConcierto.setFecha(conciertoDTO.getFecha());
        nuevoConcierto.setPrecio(conciertoDTO.getPrecio());
        nuevoConcierto.setRecinto(conciertoDTO.getRecinto());
        nuevoConcierto.setCantante(cantante);
        return conciertoRepository.save(nuevoConcierto);
    }


    @Override
    public Concierto modifyConcierto(long id, Concierto nuevoConcierto) {
        Concierto concierto = conciertoRepository.findById(id)
                .orElseThrow(() -> new ConciertoNotFoundException(id));
        nuevoConcierto.setId(concierto.getId());
        return conciertoRepository.save(nuevoConcierto);
    }

    @Override
    public void deleteConcierto(long id) {
        conciertoRepository.findById(id)
                .orElseThrow(() -> new ConciertoNotFoundException(id));
        conciertoRepository.deleteById(id);

    }
}
