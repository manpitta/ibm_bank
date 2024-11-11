package com.IBM.IBM_bank.Services.Exceptions;

public class DataIntegrityViolationException  extends RuntimeException{

    public DataIntegrityViolationException(String msg){
        super(msg);
    }
}
