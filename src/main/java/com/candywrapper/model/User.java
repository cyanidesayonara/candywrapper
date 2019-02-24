package com.candywrapper.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Document(collection = "User")
public class User {

    @Id
    private String id;
    private String name;
    private String description;

    public User(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}