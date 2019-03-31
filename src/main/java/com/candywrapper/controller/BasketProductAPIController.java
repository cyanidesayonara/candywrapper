package com.candywrapper.controller;

import com.candywrapper.model.BasketProduct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.candywrapper.service.BasketProductService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BasketProductAPIController {
    
    public static final Logger logger = LoggerFactory.getLogger(BasketProductAPIController.class);

    @Autowired
    private BasketProductService basketProductService;

    @GetMapping("/basketproducts/")
    public ResponseEntity<List<BasketProduct>> findAll() {
        List<BasketProduct> basketProducts = basketProductService.findAll();
        if (basketProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<BasketProduct>>(basketProducts, HttpStatus.OK);
    }

    @GetMapping("/basketproducts/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        logger.info("Fetching BasketProduct with id {}" , id);
        BasketProduct basketProduct = basketProductService.findById(id);
        if (basketProduct == null) {
            logger.error("BasketProduct with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<BasketProduct>(basketProduct, HttpStatus.OK);
    }

    @PostMapping("/basketproducts/")
    public ResponseEntity<?> save(@RequestBody BasketProduct basketProduct) {
        logger.info("Creating BasketProduct : {}", basketProduct);
        if (basketProductService.existsByProduct(basketProduct.getProduct())) {
            logger.info("A BasketProduct with Product {} already exists, adding", basketProduct.getProduct());
            BasketProduct oldBasketProduct = basketProductService.findByProduct(basketProduct.getProduct());

            basketProduct.setId(oldBasketProduct.getId());
            basketProduct.setAmount(Integer.toString(Integer.parseInt(oldBasketProduct.getAmount()) + Integer.parseInt(basketProduct.getAmount())));

            BasketProduct updatedBasketProduct = basketProductService.update(basketProduct);
            return new ResponseEntity<BasketProduct>(updatedBasketProduct, HttpStatus.OK);
        }
        BasketProduct createdBasketProduct = basketProductService.save(basketProduct);
        return new ResponseEntity<BasketProduct>(createdBasketProduct, HttpStatus.OK);
    }

    @PutMapping("/basketproducts/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody BasketProduct basketProduct) {
        logger.info("Updating BasketProduct with id {}", id);

        BasketProduct currentBasketProduct = basketProductService.findById(id);
        if (currentBasketProduct == null) {
            logger.error("BasketProduct with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentBasketProduct.setId(basketProduct.getId());

        BasketProduct updatedBasketProduct = basketProductService.update(basketProduct);
        return new ResponseEntity<BasketProduct>(updatedBasketProduct, HttpStatus.OK);
    }

    @DeleteMapping("/basketproducts/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        logger.info("Fetching and deleting BasketProduct with id {}", id);

        if (basketProductService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        basketProductService.deleteById(id);
        return new ResponseEntity<BasketProduct>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/basketproducts/")
    public ResponseEntity<?> deleteAll() {
        logger.info("Deleting all BasketProducts");

        basketProductService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}