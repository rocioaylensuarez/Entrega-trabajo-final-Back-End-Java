package com.suarez.ropa.exception;

public class ProductoNoEncontradoException extends RuntimeException {
    
    public ProductoNoEncontradoException (String mensaje){
        super(mensaje);
    }

}
