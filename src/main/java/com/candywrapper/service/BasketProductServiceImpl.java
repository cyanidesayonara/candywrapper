package com.candywrapper.service;

import java.util.List;

import com.candywrapper.model.Product;
import com.candywrapper.model.BasketProduct;
import com.candywrapper.repository.BasketProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketProductServiceImpl implements BasketProductService {
    
    @Autowired
    private BasketProductRepository basketProductRepository;

    @Override
    public List<BasketProduct> findAll() {
        return basketProductRepository.findAll();
    }

    @Override
    public BasketProduct findById(String id) {
        return basketProductRepository.findById(id).get();
    }

    @Override
    public boolean existsById(String id) {
        return basketProductRepository.existsById(id);
    }

    @Override
    public boolean existsByProduct(Product product) {
        return basketProductRepository.existsByProduct(product);
    }

    @Override
    public BasketProduct save(BasketProduct basketProduct) {
        return basketProductRepository.save(basketProduct);
    }

    @Override
    public BasketProduct update(BasketProduct basketProduct) {
        return basketProductRepository.save(basketProduct);
    }

    @Override
    public void deleteById(String id) {
        basketProductRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        basketProductRepository.deleteAll();
    }    
}