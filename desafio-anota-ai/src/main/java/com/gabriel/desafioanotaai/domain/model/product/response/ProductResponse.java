package com.gabriel.desafioanotaai.domain.model.product.response;

import com.gabriel.desafioanotaai.application.dtos.ProductDTO;
import com.gabriel.desafioanotaai.domain.model.category.Category;
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

    public ProductResponse(ProductDTO productDTO) {
        this.title = productDTO.getTitle();
        this.description = productDTO.getDescription();
        this.ownerId = productDTO.getOwnerId();
        this.price = productDTO.getPrice();
    }
}
