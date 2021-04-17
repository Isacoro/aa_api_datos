package com.isabel.aa_api_datos.exception;

public class ConciertoNotFoundException extends RuntimeException {

    public ConciertoNotFoundException(){
        super();
    }

    public ConciertoNotFoundException(String message){
        super(message);
    }

    public ConciertoNotFoundException(long id){
        super("Canción Not Found: " + id);
    }
}
