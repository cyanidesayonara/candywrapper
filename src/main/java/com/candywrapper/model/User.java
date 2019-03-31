package com.candywrapper.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.candywrapper.model.Role;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Document(collection = "User")
public class User {

    @Id
    private String id;

    @Size(min=4, max=32)
    @NotBlank(message="Username is mandatory")
    private String username;

    @Size(min=8, max=32)
    @NotBlank(message="Password is mandatory")
    private String password;

    @Transient
    private String passwordConfirm;
    
    @DBRef
    private Set<Role> roles;

    public User(String id, String username, String password, String passwordConfirm) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }
}