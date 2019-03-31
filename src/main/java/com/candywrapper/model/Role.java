package com.candywrapper.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.candywrapper.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Document(collection = "Role")
public class Role {
    
    @Id
    private String id;

    @NotBlank(message="Name is mandatory")
    private String name;
    private Set<User> users;

    public Role(String id, String name) {
        this.id = id;
        this.name = name;
    }
}