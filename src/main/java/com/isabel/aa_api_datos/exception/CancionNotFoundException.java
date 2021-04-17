package com.isabel.aa_api_datos.exception;

public class CancionNotFoundException extends RuntimeException {

    public CancionNotFoundException(){
        super();
    }

    public CancionNotFoundException(String message){
        super(message);
    }

    public CancionNotFoundException(long id){
        super("Canción Not Found: " + id);
    }
}
