package com.candywrapper.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Document(collection = "User")
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;
    private String password;

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }    
}
