package com.gabriel.desafioanotaai.application.dtos;

import com.gabriel.desafioanotaai.domain.model.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
        private String id;
        private String title;
        private String description;
        private String ownerId;
        private Integer price;
        private String categoryId;

        public ProductDTO(String id, String title, String description, String ownerId, Integer price, String categoryId) {
                this.id = id;
                this.title = title;
                this.description = description;
                this.ownerId = ownerId;
                this.price = price;
                this.categoryId = categoryId;
        }
}
