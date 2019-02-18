package com.candywrapper.controller;

import com.candywrapper.model.Candy;

import org.springframework.beans.factory.annotation.Autowired;
import com.candywrapper.service.CandyService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;



@RestController
public class CandyController {

    @Autowired
    private CandyService candyService;

    @GetMapping("/api/candies")
    public List<Candy> hello() {
        return candyService.findAll();
    }

    @PostMapping("/api/candies")
    public Candy save(Candy candy) {
        return candyService.save(candy);
    }

    @PutMapping("/api/candies")
    public Candy update(Candy candy) {
        return candyService.update(candy);
    }

    @DeleteMapping("/api/candies")
    public void delete(@RequestParam("id")String id) {
        candyService.delete(id);
    }
    
}