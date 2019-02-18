package com.candywrapper.service;

import java.util.List;

import com.candywrapper.model.Candy;

public interface CandyService {
    List<Candy> findAll();
    Candy save(Candy candy);
    Candy update(Candy candy);
    void delete(String id);
}