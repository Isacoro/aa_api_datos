package com.isabel.aa_api_datos.controller;

import com.isabel.aa_api_datos.domain.Cantante;
import com.isabel.aa_api_datos.service.CantanteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class CantanteController {

    @Autowired
    public CantanteService cantanteService;

    private final Logger logger = LoggerFactory.getLogger(CantanteController.class);

    @GetMapping("/cantantes")
    public ResponseEntity<Set<Cantante>> getCantantes(){
        Set<Cantante> cantantes = cantanteService.findAll();
        return new ResponseEntity<>(cantantes, HttpStatus.OK);
    }
}
