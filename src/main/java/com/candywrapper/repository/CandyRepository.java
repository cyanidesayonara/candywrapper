package com.candywrapper.repository;

import java.util.List;

import com.candywrapper.model.Candy;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CandyRepository extends MongoRepository<Candy, String> {

    @Override
    List<Candy> findAll();

    @Query("{ 'candyName': ?0 }")
    List<Candy> findCandyByName(String name);

    Candy findCandyById(String id);

    @Override
    void deleteById(String id);
}