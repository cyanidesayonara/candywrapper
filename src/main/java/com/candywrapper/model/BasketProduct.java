package com.candywrapper.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Document(collection = "BasketProduct")
public class BasketProduct {

    @Id
    private String id;
    private String basket;
    private String product;
    private String amount;

    public BasketProduct(String id, String basket, String product, String amount) {
        this.id = id;
        this.basket = basket;
        this.product = product;
        this.amount = amount;
    }
}