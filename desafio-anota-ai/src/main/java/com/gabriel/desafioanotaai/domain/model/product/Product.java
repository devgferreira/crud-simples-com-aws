package com.gabriel.desafioanotaai.domain.model.product;

import com.gabriel.desafioanotaai.application.dtos.ProductDTO;
import com.gabriel.desafioanotaai.domain.model.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
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

    @Override
    public String toString(){
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("description", description);
        json.put("ownerId", ownerId);
        json.put("price", price);
        json.put("categoryId", category);
        json.put("id", id);
        json.put("type", "product");

        return json.toString();
    }
}
