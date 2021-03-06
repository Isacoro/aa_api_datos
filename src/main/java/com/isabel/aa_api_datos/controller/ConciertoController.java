package com.isabel.aa_api_datos.controller;

import com.isabel.aa_api_datos.domain.Cantante;
import com.isabel.aa_api_datos.domain.Concierto;
import com.isabel.aa_api_datos.domain.dto.ConciertoDTO;
import com.isabel.aa_api_datos.exception.CantanteNotFoundException;
import com.isabel.aa_api_datos.exception.ConciertoNotFoundException;
import com.isabel.aa_api_datos.service.ConciertoService;
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
@Tag(name = "Conciertos", description = "Listado de conciertos")
public class ConciertoController {

    @Autowired
    public ConciertoService conciertoService;

    private final Logger logger = LoggerFactory.getLogger(ConciertoController.class);


    @Operation(summary = "Obtener el listado de los conciertos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de conciertos", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Concierto.class))))
    })
    //Todos los conciertos
    @GetMapping(value = "/conciertos", produces = "application/json")
    public ResponseEntity<Set<Concierto>> getConciertos(){
        logger.info("Inicio getConciertos");
        Set<Concierto> conciertos = conciertoService.findAll();
        logger.info("Fin getConciertos");
        return new ResponseEntity<>(conciertos, HttpStatus.OK);
    }



    @Operation(summary = "Obtener el listado de los conciertos por localidad, celebrado y precio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de conciertos por localidad, celebrado y precio", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Concierto.class))))
    })
    //Todos los conciertos por localidad, celebrado y precio
    @GetMapping(value = "/conciertos/localidad-celebrado-precio", produces = "application/json")
    public ResponseEntity<Set<Concierto>> getConciertosByLocalidadCelebradoPrecio(
                                                    @RequestParam(value = "localidad", defaultValue = "") String localidad,
                                                    @RequestParam(value = "celebrado", defaultValue = "") boolean celebrado,
                                                    @RequestParam(value = "precio", defaultValue = "") float precio){
        logger.info("Inicio getConciertosByLocalidadCelebradoPrecio");
        Set<Concierto> conciertos = conciertoService.findByLocalidadAndCelebradoAndPrecio(localidad, celebrado, precio);
        logger.info("Fin getConciertosByLocalidadCelebradoPrecio");
        return new ResponseEntity<>(conciertos, HttpStatus.OK);
    }


    @Operation(summary = "Registrar un concierto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra el concierto", content = @Content(schema = @Schema(implementation = Concierto.class))),
    })
    //A??adir concierto
    @PostMapping(value = "/conciertos", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Concierto> addConcierto(@RequestBody ConciertoDTO conciertoDTO){
        logger.info("Inicio a??adir concierto");
        Concierto addedConcierto = conciertoService.addConcierto(conciertoDTO);
        logger.info("A??adido concierto");
        return ResponseEntity.status(HttpStatus.CREATED).body(addedConcierto);
    }


    @Operation(summary = "Modificar un concierto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el concierto", content = @Content(schema = @Schema(implementation = Concierto.class))),
            @ApiResponse(responseCode = "404", description = "El concierto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    //Modificar concierto
    @PutMapping(value = "/conciertos/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Concierto> modifyConcierto(@PathVariable long id, @RequestBody Concierto nuevoConcierto){
        logger.info("Inicio modificar concierto");
        Concierto concierto = conciertoService.modifyConcierto(id, nuevoConcierto);
        logger.info("Modificado concierto");
        return new ResponseEntity<>(concierto, HttpStatus.OK);
    }


    @Operation(summary = "Eliminar un concierto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el concierto", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El concierto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    //Borrar concierto
    @DeleteMapping(value = "/conciertos/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteConcierto(@PathVariable long id){
        logger.info("Inicio eliminar concierto");
        conciertoService.deleteConcierto(id);
        logger.info("Eliminado concierto");
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }


    @Operation(summary = "Modifica el aforo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el aforo del concierto", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El concierto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    //Modificar el aforo del concierto
    @PatchMapping(value = "/conciertos/{id}/change-aforo", produces = "application/json")
    public ResponseEntity<Concierto> cambiaConcierto(@PathVariable long id, @RequestBody long aforo){
        logger.info("Inicio modificar aforo del concierto");
        Concierto concierto = conciertoService.findConciertoById(id)
                .orElseThrow(() -> new ConciertoNotFoundException(id));
        concierto.setAforo(aforo);
        conciertoService.modifyConcierto(id, concierto);
        logger.info("Fin modificado aforo del concierto");
        return new ResponseEntity<>(concierto, HttpStatus.OK);
    }





    //Excepciones en caso de no encontrar el elemento
    @ExceptionHandler(ConciertoNotFoundException.class)
    @ResponseBody
    @ResponseStatus
    public ResponseEntity<Response> handleException(ConciertoNotFoundException cnfe){
        Response response = Response.errorResponse(Response.NOT_FOUND, cnfe.getMessage());
        logger.error(cnfe.getMessage(), cnfe);  //Logger para el error
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
