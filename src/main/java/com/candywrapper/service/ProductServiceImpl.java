package com.candywrapper.service;

import java.util.List;
import java.util.Optional;

import com.candywrapper.model.Product;
import com.candywrapper.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id).get();
    }

    @Override
    public boolean findProductByNameIgnoreCase(Product product) {
        return !productRepository.findProductByNameIgnoreCase(product.getName()).isEmpty();
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
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
        }
    }

    @Override
    public void deleteAllProducts() {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            productRepository.delete(product);
        }
    }    
}