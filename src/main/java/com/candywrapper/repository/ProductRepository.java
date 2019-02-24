package com.candywrapper.repository;

import java.util.List;

import com.candywrapper.model.Product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    @Override
    List<Product> findAll();

    @Query("{ 'name': ?0 }")
    List<Product> findByName(String name);

    List<Product> findProductByNameIgnoreCase(String name);

    Product findById(String id);

    @Override
    void deleteById(String id);
}