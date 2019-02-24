package com.candywrapper.repository;

import java.util.List;

import com.candywrapper.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Override
    List<User> findAll();

    @Query("{ 'userName': ?0 }")
    List<User> findUserByName(String name);

    List<User> findUserByNameIgnoreCase(String name);

    User findUserById(String id);

    @Override
    void deleteById(String id);
}