package com.isabel.aa_api_datos.controller;

import com.isabel.aa_api_datos.domain.Disco;
import com.isabel.aa_api_datos.service.DiscoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@Tag(name = "Discos", description = "Listado de discos")
public class DiscoController {

    @Autowired
    public DiscoService discoService;

    private final Logger logger = LoggerFactory.getLogger(DiscoController.class);

    @GetMapping("/discos")
    public ResponseEntity<Set<Disco>> getDiscos(){
        Set<Disco> discos = discoService.findAll();
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }
}
