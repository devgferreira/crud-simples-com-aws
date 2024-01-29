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
        private String title;
        private String description;
        private String ownerId;
        private Integer price;
        private Category category;

}
