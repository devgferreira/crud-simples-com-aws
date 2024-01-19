package com.gabriel.desafioanotaai.domain.model.category;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document("categories")
public class Category {
    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
}
