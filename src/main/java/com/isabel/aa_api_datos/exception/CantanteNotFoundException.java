package com.isabel.aa_api_datos.exception;

public class CantanteNotFoundException extends RuntimeException {

    public CantanteNotFoundException(){
        super();
    }

    public CantanteNotFoundException(String message){
        super(message);
    }

    public CantanteNotFoundException(long id){
        super("Cantante Not Found: " + id);
    }
}
