package com.candywrapper.model;

import java.util.Set;

import com.candywrapper.model.Role;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Document(collection = "User")
public class User {

    @Id
    private String id;
    private String username;
    private String password;

    @Transient
    private String passwordConfirm;
    private Set<Role> roles;

    public User(String id, String username, String password, String passwordConfirm) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }
}