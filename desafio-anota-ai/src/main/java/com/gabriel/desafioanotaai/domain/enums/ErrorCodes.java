package com.gabriel.desafioanotaai.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {

    CATEGORY_NAO_ENCONTRADO("Category não foi encontrado"),
    PRODUCT_NAO_ENCONTRADO("Product não foi encontrado");
    private final String message;

}