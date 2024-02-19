package com.gabriel.crudsimplescomaws.infra.exceptions.handler;

import com.gabriel.crudsimplescomaws.infra.exceptions.CategoryNaoEncontradoException;
import com.gabriel.crudsimplescomaws.infra.exceptions.ExceptionResponse;
import com.gabriel.crudsimplescomaws.infra.exceptions.ProductNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.gabriel.crudsimplescomaws.domain.enums.ErrorCodes;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ProductNaoEncontradoException.class)
    public final ResponseEntity<Object> handleProductNaoEncontradoException(ProductNaoEncontradoException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.PRODUCT_NAO_ENCONTRADO, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
    @ExceptionHandler(CategoryNaoEncontradoException.class)
    public final ResponseEntity<Object> handleCategoryNaoEncontradoException(CategoryNaoEncontradoException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.CATEGORY_NAO_ENCONTRADO, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
}
