package com.gabriel.crudsimplescomaws.domain.model.product.response;

import com.gabriel.crudsimplescomaws.application.dtos.ProductDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponse {
    private String title;
    private String description;
    private String ownerId;
    private Integer price;
    private String categoryId;

    public ProductResponse(ProductDTO productDTO) {
        this.title = productDTO.getTitle();
        this.description = productDTO.getDescription();
        this.ownerId = productDTO.getOwnerId();
        this.price = productDTO.getPrice();
        this.categoryId = productDTO.getCategoryId();
    }
}
