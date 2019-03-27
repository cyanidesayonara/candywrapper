package com.candywrapper.repository;

import java.util.List;

import com.candywrapper.model.Role;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

    @Override
    List<Role> findAll();

    Role findOneByName(String name);

    @Query("{ 'userName': ?0 }")
    List<Role> findByName(String name);

    boolean existsByName(String name);

    @Override
    void deleteById(String id);

    @Override
    void deleteAll();
}