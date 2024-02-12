package com.gabriel.desafioanotaai.domain.model.product;

import com.gabriel.desafioanotaai.application.dtos.ProductDTO;
import com.gabriel.desafioanotaai.domain.model.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document("products")
public class Product {
    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price;
    private String category;

    public Product(ProductDTO productDTO){
        title = productDTO.getTitle();
        description = productDTO.getDescription();
        ownerId = productDTO.getOwnerId();
        price = productDTO.getPrice();
        category = productDTO.getCategoryId();
    }
}
