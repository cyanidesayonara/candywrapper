package com.candywrapper.model;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Document(collection = "Product")
public class Product {

    @Id
    private String id;

    @NotBlank(message="Name is mandatory")
    private String name;

    @NotBlank(message="Description is mandatory")
    private String description;

    public Product(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}