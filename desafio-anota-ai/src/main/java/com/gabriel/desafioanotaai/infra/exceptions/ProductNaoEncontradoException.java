package com.gabriel.desafioanotaai.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNaoEncontradoException  extends RuntimeException{
    private ExceptionResponse exceptionResponse;

    public ProductNaoEncontradoException(ExceptionResponse exceptionResponse){
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
