package com.isabel.aa_api_datos.controller;

import com.isabel.aa_api_datos.domain.Concierto;
import com.isabel.aa_api_datos.service.ConciertoService;
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
@Tag(name = "Conciertos", description = "Listado de conciertos")
public class ConciertoController {

    @Autowired
    public ConciertoService conciertoService;

    private final Logger logger = LoggerFactory.getLogger(ConciertoController.class);

    @GetMapping("/conciertos")
    public ResponseEntity<Set<Concierto>> getConciertos(){
        Set<Concierto> conciertos = conciertoService.findAll();
        return new ResponseEntity<>(conciertos, HttpStatus.OK);
    }
}
