package com.gabriel.desafioanotaai.domain.model.category;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
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

    public Category(String id, String title, String description, String ownerId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ownerId = ownerId;
    }

    @Override
    public String toString(){
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("description", description);
        json.put("ownerId", ownerId);
        json.put("id", id);
        json.put("type", "category");
        return json.toString();
    }
}
