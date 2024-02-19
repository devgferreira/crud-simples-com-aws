package com.gabriel.crudsimplescomaws.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNaoEncontradoException extends RuntimeException {
    private ExceptionResponse exceptionResponse;

    public CategoryNaoEncontradoException(ExceptionResponse exceptionResponse){
        super();
        this.exceptionResponse = exceptionResponse;
    }
}
