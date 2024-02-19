package com.gabriel.crudsimplescomaws.domain.model.category.Response;

import com.gabriel.crudsimplescomaws.application.dtos.CategoryDTO;
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
