package com.gabriel.desafioanotaai.infra.exceptions;

import com.gabriel.desafioanotaai.domain.enums.ErrorCodes;
import lombok.Data;

@Data
public class ExceptionResponse {
    private final String code;
    private final String message;
    public ExceptionResponse(final ErrorCodes errorCode, String details) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
    }

}