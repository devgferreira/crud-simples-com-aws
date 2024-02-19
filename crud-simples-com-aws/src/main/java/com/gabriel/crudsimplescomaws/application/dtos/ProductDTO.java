package com.gabriel.crudsimplescomaws.application.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
