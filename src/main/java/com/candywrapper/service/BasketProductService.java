package com.candywrapper.service;

import java.util.List;

import com.candywrapper.model.BasketProduct;

public interface BasketProductService {
    List<BasketProduct> findAll();
    BasketProduct findById(String id);
    boolean existsById(String id);
    BasketProduct save(BasketProduct basketProduct);
    BasketProduct update(BasketProduct basketProduct);
    void deleteById(String id);
    void deleteAll();
}