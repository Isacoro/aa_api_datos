package com.isabel.aa_api_datos.controller;

import com.isabel.aa_api_datos.domain.Cancion;
import com.isabel.aa_api_datos.domain.dto.CancionDTO;
import com.isabel.aa_api_datos.exception.CancionNotFoundException;
import com.isabel.aa_api_datos.service.CancionService;
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
@Tag(name = "Canciones", description = "Listado de canciones")
public class CancionController {

    private final Logger logger = LoggerFactory.getLogger(CancionController.class);


    @Autowired
    public CancionService cancionService;

   @Operation(summary = "Obtiene el listado de las canciones")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Listado de canciones", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Cancion.class)))),
   })
    //Todas las canciones
    @GetMapping(value = "/canciones", produces = "application/json")
    public ResponseEntity<Set<Cancion>> getCanciones(){
        logger.info("Inicio getCanciones");
        Set<Cancion> canciones = cancionService.findAll();
        logger.info("Fin getCanciones");
        return new ResponseEntity<>(canciones, HttpStatus.OK);
    }


    @Operation(summary = "Registra una canción")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra la canción", content = @Content(schema = @Schema(implementation = Cancion.class)))
    })
    //Añadir canción
    @PostMapping(value = "/canciones", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Cancion> addCancion(@RequestBody CancionDTO cancionDTO){
        logger.info("Inicio añadir canción");
        Cancion addedCancion = cancionService.addCancion(cancionDTO);
        logger.info("Añadida canción");
        return ResponseEntity.status(HttpStatus.CREATED).body(addedCancion);
    }



    @Operation(summary = "Modifica una canción")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica la canción", content = @Content(schema = @Schema(implementation = Cancion.class))),
            @ApiResponse(responseCode = "404", description = "La canción no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    //Modificar canción
    @PutMapping(value = "/canciones/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Cancion> modifyCancion(@PathVariable long id, @RequestBody Cancion nuevaCancion){
        logger.info("Inicio modificar canción");
        Cancion cancion = cancionService.modifyCancion(id, nuevaCancion);
        logger.info("Modificada canción");
        return new ResponseEntity<>(cancion, HttpStatus.OK);
    }



    @Operation(summary = "Elimina una canción")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina la canción", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "La canción no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    //Borrar canción
    @DeleteMapping(value = "/canciones/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteCancion(@PathVariable long id){
        logger.info("Inicio eliminar canción");
        cancionService.deleteCancion(id);
        logger.info("Eliminada canción");
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }



    @Operation(summary = "Modifica el título de una cación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el título de la canción", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "La canción no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    //Modificar título de la canción
    @PatchMapping(value = "/canciones/{id}/change-titulo", produces = "application/json")
    public ResponseEntity<Cancion> cambiaCancion(@PathVariable long id, @RequestBody String titulo){
        logger.info("Inicio modificar titulo de la canción");
        Cancion cancion = cancionService.findCancionById(id)
                .orElseThrow(() -> new CancionNotFoundException(id));
        cancion.setTitulo(titulo);
        cancionService.modifyCancion(id, cancion);
        logger.info("Fin modificado título de canción");
        return new ResponseEntity<>(cancion, HttpStatus.OK);
    }



    //Excepciones en caso de no encontrar el elemento
    @ExceptionHandler(CancionNotFoundException.class)
    @ResponseBody
    @ResponseStatus
    public ResponseEntity<Response> handleException(CancionNotFoundException cnfe){
        Response response = Response.errorResponse(Response.NOT_FOUND, cnfe.getMessage());
        logger.error(cnfe.getMessage(), cnfe);  //Logger para el error
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
