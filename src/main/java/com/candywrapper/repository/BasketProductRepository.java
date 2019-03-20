package com.candywrapper.repository;

import java.util.List;

import com.candywrapper.model.Product;
import com.candywrapper.model.BasketProduct;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketProductRepository extends MongoRepository<BasketProduct, String> {

    @Override
    List<BasketProduct> findAll();

    boolean existsById(String id);

    boolean existsByProduct(Product product);

    @Override
    void deleteById(String id);

    @Override
    void deleteAll();    
}