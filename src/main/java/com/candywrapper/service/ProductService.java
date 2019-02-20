package com.candywrapper.service;

import java.util.List;

import com.candywrapper.model.Product;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(String id);
    Product save(Product product);
    Product update(Product product);
    void delete(String id);
}