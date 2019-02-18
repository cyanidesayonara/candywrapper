package com.candywrapper.service;

import java.util.List;
import java.util.Optional;

import com.candywrapper.model.Candy;
import com.candywrapper.repository.CandyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandyServiceImplementation implements CandyService {
    
    @Autowired
    private CandyRepository candyRepository;

    @Override
    public List<Candy> findAll() {
        return candyRepository.findAll();
    }

    @Override
    public Candy save(Candy candy) {
        return candyRepository.save(candy);
    }

    @Override
    public Candy update(Candy candy) {
        return candyRepository.save(candy);
    }

    @Override
    public void delete(String id) {
        Optional<Candy> o = candyRepository.findById(id);
        if (o.isPresent()) {
            candyRepository.delete(o.get());
        }
    }
}