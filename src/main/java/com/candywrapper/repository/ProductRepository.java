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

    @Query("{ 'productName': ?0 }")
    List<Product> findByName(String name);

    List<Product> findByNameIgnoreCase(String name);

    boolean existsByName(String name);

    @Override
    void deleteById(String id);

    @Override
    void deleteAll();    
}