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

    User findOneByUsername(String Username);
    
    @Query("{ 'userUsername': ?0 }")
    List<User> findByUsername(String Username);

    List<User> findByUsernameIgnoreCase(String username);

    boolean existsByUsername(String username);

    @Override
    void deleteById(String id);

    @Override
    void deleteAll();
}