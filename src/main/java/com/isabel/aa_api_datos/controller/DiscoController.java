package com.isabel.aa_api_datos.controller;

import com.isabel.aa_api_datos.domain.Concierto;
import com.isabel.aa_api_datos.domain.Disco;
import com.isabel.aa_api_datos.domain.dto.DiscoDTO;
import com.isabel.aa_api_datos.exception.ConciertoNotFoundException;
import com.isabel.aa_api_datos.exception.DiscoNotFoundException;
import com.isabel.aa_api_datos.service.DiscoService;
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

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@RestController
@Tag(name = "Discos", description = "Listado de discos")
public class DiscoController {

    @Autowired
    public DiscoService discoService;

    private final Logger logger = LoggerFactory.getLogger(DiscoController.class);


    @Operation(summary = "Obtener el listado de discos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de discos", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Disco.class))))
    })
    //Todas los discos
    @GetMapping(value = "/discos", produces = "application/json")
    public ResponseEntity<Set<Disco>> getDiscos(){
        logger.info("Inicio getDiscos");
        Set<Disco> discos = discoService.findAll();
        logger.info("Fin getDiscos");
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }


    @Operation(summary = "Registrar una disco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra el disco", content = @Content(schema = @Schema(implementation = Disco.class))),
    })
    //Añadir disco
    @PostMapping(value = "/discos", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Disco> addDisco(@RequestBody DiscoDTO discoDTO){
        logger.info("Inicio añadir disco");
        Disco addedDisco = discoService.addDisco(discoDTO);
        logger.info("Añadido disco");
        return ResponseEntity.status(HttpStatus.CREATED).body(addedDisco);
    }




    @Operation(summary = "Modificar un disco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el disco", content = @Content(schema = @Schema(implementation = Disco.class))),
            @ApiResponse(responseCode = "404", description = "El disco no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    //Modificar disco
    @PutMapping(value = "/discos/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Disco> modifyDisco(@PathVariable long id, @RequestBody Disco nuevoDisco){
        logger.info("Inicio modificar disco");
        Disco disco = discoService.modifyDisco(id, nuevoDisco);
        logger.info("Modificado disco");
        return new ResponseEntity<>(disco, HttpStatus.OK);
    }



    @Operation(summary = "Eliminar un disco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el disco", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El disco no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    //Borrar disco
    @DeleteMapping(value = "/discos/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteDisco(@PathVariable long id){
        logger.info("Inicio eliminar disco");
        discoService.deleteDisco(id);
        logger.info("Eliminado disco");
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }


    @Operation(summary = "Modifica si ha sido premiado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica si ha sido premiado", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El disco no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    //Modificar si el disco ha sido premiado
    @PatchMapping(value = "/discos/{id}/change-premiado", produces = "application/json")
    public ResponseEntity<Disco> cambiaDisco(@PathVariable long id, @RequestBody boolean premiado){
        logger.info("Inicio modificar si el disco ha sido premiado");
        Disco disco = discoService.findDiscoById(id)
                .orElseThrow(() -> new DiscoNotFoundException(id));
        disco.setPremiado(premiado);
        discoService.modifyDisco(id, disco);
        logger.info("Fin modificado premiado");
        return new ResponseEntity<>(disco, HttpStatus.OK);
    }



    //Excepciones en caso de no encontrar el elemento
    @ExceptionHandler(DiscoNotFoundException.class)
    @ResponseBody
    @ResponseStatus
    public ResponseEntity<Response> handleException(DiscoNotFoundException dnfe){
        Response response = Response.errorResponse(Response.NOT_FOUND, dnfe.getMessage());
        logger.error(dnfe.getMessage(), dnfe);  //Logger para el error
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
