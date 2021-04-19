package com.isabel.aa_api_datos.service;

import com.isabel.aa_api_datos.domain.Cantante;
import com.isabel.aa_api_datos.domain.Disco;
import com.isabel.aa_api_datos.domain.dto.DiscoDTO;
import com.isabel.aa_api_datos.exception.CantanteNotFoundException;
import com.isabel.aa_api_datos.exception.DiscoNotFoundException;
import com.isabel.aa_api_datos.repository.CantanteRepository;
import com.isabel.aa_api_datos.repository.DiscoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class DiscoServiceImpl implements DiscoService {

    @Autowired
    private DiscoRepository discoRepository;

    @Autowired
    private CantanteRepository cantanteRepository;



    @Override
    public Set<Disco> findAll() {
        return discoRepository.findAll();
    }

    @Override
    public Optional<Disco> findDiscoById(long id) {
        return discoRepository.findById(id);
    }

    @Override
    public Disco addDisco(DiscoDTO discoDTO) {
        Cantante cantante = cantanteRepository.findById(discoDTO.getCantante_id())
                .orElseThrow(() -> new CantanteNotFoundException("Cantante no encontrado"));
        Disco nuevoDisco = new Disco();
        nuevoDisco.setNombre(discoDTO.getNombre());
        nuevoDisco.setFechaSalida(discoDTO.getFechaSalida());
        nuevoDisco.setPrecio(discoDTO.getPrecio());
        nuevoDisco.setCantante(cantante);
        return discoRepository.save(nuevoDisco);
    }

    @Override
    public Disco modifyDisco(long id, Disco nuevoDisco) {
        Disco disco = discoRepository.findById(id)
                .orElseThrow(() -> new DiscoNotFoundException(id));
        nuevoDisco.setId(disco.getId());
        return discoRepository.save(nuevoDisco);
    }

    @Override
    public void deleteDisco(long id) {
        discoRepository.findById(id)
                .orElseThrow(() -> new DiscoNotFoundException(id));
        discoRepository.deleteById(id);
    }
}
