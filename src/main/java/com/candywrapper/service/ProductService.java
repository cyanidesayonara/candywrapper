package com.candywrapper.service;

import java.util.List;

import com.candywrapper.model.Product;

public interface ProductService {
    List<Product> findAll();
    Product findById(String id);
    List<Product> findByName(String name);
    boolean existsByName(String name);
    Product save(Product product);
    Product update(Product product);
    void deleteById(String id);
    void deleteAll();
}