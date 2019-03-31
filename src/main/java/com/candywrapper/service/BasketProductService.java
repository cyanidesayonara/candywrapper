package com.candywrapper.service;

import java.util.List;

import com.candywrapper.model.Product;
import com.candywrapper.model.BasketProduct;

public interface BasketProductService {
    List<BasketProduct> findAll();
    BasketProduct findById(String id);
    BasketProduct findByProduct(Product product);
    boolean existsById(String id);
    boolean existsByProduct(Product product);
    BasketProduct save(BasketProduct basketProduct);
    BasketProduct update(BasketProduct basketProduct);
    void deleteById(String id);
    void deleteAll();
}