package com.gabriel.desafioanotaai.application.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    private String id;
    private String title;
    private String description;
    private String ownerId;
}
