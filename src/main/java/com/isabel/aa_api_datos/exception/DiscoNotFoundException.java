package com.isabel.aa_api_datos.exception;

public class DiscoNotFoundException extends RuntimeException {

    public DiscoNotFoundException(){
        super();
    }

    public DiscoNotFoundException(String message){
        super(message);
    }

    public DiscoNotFoundException(long id){
        super("Canción Not Found: " + id);
    }
}
