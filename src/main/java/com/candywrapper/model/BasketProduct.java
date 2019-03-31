package com.candywrapper.model;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Document(collection = "BasketProduct")
public class BasketProduct {

    @Id
    private String id;

    @DBRef
    private Product product;

    @NotBlank(message="Amount is mandatory")
    private String amount;

    public BasketProduct(String id, Product product, String amount) {
        this.id = id;
        this.product = product;
        this.amount = amount;
    }
}