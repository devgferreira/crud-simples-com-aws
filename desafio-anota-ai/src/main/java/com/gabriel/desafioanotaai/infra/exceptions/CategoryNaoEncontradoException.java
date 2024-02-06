package com.gabriel.desafioanotaai.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNaoEncontradoException {
    private ExceptionResponse exceptionResponse;

    public CategoryNaoEncontradoException(ExceptionResponse exceptionResponse){
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
