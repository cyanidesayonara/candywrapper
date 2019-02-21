package com.candywrapper.service;

import java.util.List;

import com.candywrapper.model.Product;

public interface ProductService {
    List<Product> findAll();
    Product findById(String id);
    boolean findProductByNameIgnoreCase(Product product);
    Product save(Product product);
    Product update(Product product);
    void delete(String id);
    void deleteAllProducts();
}