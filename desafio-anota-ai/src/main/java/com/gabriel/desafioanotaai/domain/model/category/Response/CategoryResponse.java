package com.gabriel.desafioanotaai.domain.model.category.Response;

import com.gabriel.desafioanotaai.application.dtos.CategoryDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponse {
    private String title;
    private String description;
    private String ownerId;

    public CategoryResponse(CategoryDTO newCategoryDTO) {
        title = newCategoryDTO.getTitle();
        description = newCategoryDTO.getDescription();
        ownerId = newCategoryDTO.getOwnerId();
    }
}
