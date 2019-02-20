package com.candywrapper.service;

import java.util.List;
import java.util.Optional;

import com.candywrapper.model.Product;
import com.candywrapper.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImplementation implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(String id) {
        Optional<Product> o = productRepository.findById(id);
        if (o.isPresent()) {
            productRepository.delete(o.get());
        }
    }
}