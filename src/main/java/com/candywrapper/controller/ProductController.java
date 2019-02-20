package com.candywrapper.controller;

import com.candywrapper.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import com.candywrapper.service.ProductService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;



@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/api/products/{id}")
    public Product getProductById(@PathVariable("id") String id) {
        return productService.getProductById(id);
    }

    @PostMapping("/api/products")
    public Product save(Product product) {
        return productService.save(product);
    }

    @PutMapping("/api/products")
    public Product update(Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/api/products/{id}")
    public void delete(@PathVariable("id") String id) {
        productService.delete(id);
    }
    
}