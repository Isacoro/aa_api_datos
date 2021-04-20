package com.isabel.aa_api_datos.controller;

import com.isabel.aa_api_datos.domain.Cancion;
import com.isabel.aa_api_datos.domain.Cantante;
import com.isabel.aa_api_datos.exception.CancionNotFoundException;
import com.isabel.aa_api_datos.exception.CantanteNotFoundException;
import com.isabel.aa_api_datos.service.CantanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Tag(name = "Cantantes", description = "Listado de cantantes")
public class CantanteController {

    @Autowired
    public CantanteService cantanteService;

    private final Logger logger = LoggerFactory.getLogger(CantanteController.class);


    @Operation(summary = "Obtiene el listado de los cantantes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de cantantes", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Cantante.class))))
    })
    //Todas los cantantes
    @GetMapping(value = "/cantantes", produces = "application/json")
    public ResponseEntity<Set<Cantante>> getCantantes(){
        logger.info("Inicio getCantantes");
        Set<Cantante> cantantes = cantanteService.findAll();
        logger.info("Fin getCantantes");
        return new ResponseEntity<>(cantantes, HttpStatus.OK);
    }

    //Cantantes por nombre, nacionalidad y activo
    @Operation(summary = "Obtiene el listado de los cantantes por nombre, nacionalidad y activo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de cantantes por nombre, nacionalidad y activo", content = @Content(array = @ArraySchema (schema = @Schema(implementation = Cantante.class))))
    })
    @GetMapping(value = "/cantantes/nombre-nacionalidad-activo", produces = "application/json")
    public ResponseEntity<Set<Cantante>> getCantantesByNombreNacionalidadActivo
                                                    (@RequestParam(value = "nombre", defaultValue = "") String nombre,
                                                    @RequestParam(value = "nacionalidad", defaultValue = "") String nacionalidad,
                                                    @RequestParam(value = "activo", defaultValue = "") boolean activo){
        logger.info("Inicio getCantantesByNombreNacionalidadActivo");
        Set<Cantante> cantantes = cantanteService.findByNombreAndNacionalidadAndActivo(nombre, nacionalidad, activo);
        logger.info("Fin getCantantesByNombreNacionalidadActivo");
        return new ResponseEntity<>(cantantes, HttpStatus.OK);
    }



    @Operation(summary = "Registra un cantante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra el cantante", content = @Content(schema = @Schema(implementation = Cantante.class))),
    })
    //Añadir cantante
    @PostMapping(value = "/cantantes", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Cantante> addCantante(@RequestBody Cantante cantante){
        logger.info("Inicio añadir cantante");
        Cantante addedCantante = cantanteService.addCantante(cantante);
        logger.info("Añadido cantante");
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCantante);
    }



    @Operation(summary = "Modifica un cantante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el cantante", content = @Content(schema = @Schema(implementation = Cantante.class))),
            @ApiResponse(responseCode = "404", description = "El cantante no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    //Modificar cantante
    @PutMapping(value = "/cantantes/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Cantante> modifyCantante(@PathVariable long id, @RequestBody Cantante nuevoCantante){
        logger.info("Inicio modificar cantante");
        Cantante cantante = cantanteService.modifyCantante(id, nuevoCantante);
        logger.info("Modificado cantante");
        return new ResponseEntity<>(cantante, HttpStatus.OK);
    }



    @Operation(summary = "Elimina un cantante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el cantante", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El cantante no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    //Borrar cantante
    @DeleteMapping(value = "/cantantes/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteCantante(@PathVariable long id){
        logger.info("Inicio eliminar cantante");
        cantanteService.deleteCantante(id);
        logger.info("Eliminado cantante");
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }


    @Operation(summary = "Modifica los años activos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica los años activos del cantante", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El cantante no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    //Modificar los años activos del cantante
    @PatchMapping(value = "/cantantes/{id}/change-anosActivo", produces = "application/json")
    public ResponseEntity<Cantante> cambiaCantante(@PathVariable long id, @RequestBody int anosActivo){
        logger.info("Inicio modificar años activo del cantante");
        Cantante cantante = cantanteService.findCantanteById(id)
                .orElseThrow(() -> new CantanteNotFoundException(id));
        cantante.setAnosActivo(anosActivo);
        cantanteService.modifyCantante(id, cantante);
        logger.info("Fin modificado años activo cantante");
        return new ResponseEntity<>(cantante, HttpStatus.OK);
    }




    //Excepciones en caso de no encontrar el elemento
    @ExceptionHandler(CantanteNotFoundException.class)
    @ResponseBody
    @ResponseStatus
    public ResponseEntity<Response> handleException(CantanteNotFoundException cnfe){
        Response response = Response.errorResponse(Response.NOT_FOUND, cnfe.getMessage());
        logger.error(cnfe.getMessage(), cnfe);  //Logger para el error
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
