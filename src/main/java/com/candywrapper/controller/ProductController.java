package com.candywrapper.controller;

import com.candywrapper.model.Product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.candywrapper.service.ProductService;

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
public class ProductController {
    
    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/products/")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        logger.info("Fetching product with id {}" , id);
        Product product = productService.findById(id);
        if (product == null) {
            logger.error("Product with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PostMapping("/products/")
    public ResponseEntity<?> save(@RequestBody Product product) {
        logger.info("Creating Product : {}", product);
        if (productService.findProductByNameIgnoreCase(product)) {
            logger.error("A Product with name {} already exists", product.getName());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Product createdProduct = productService.save(product);
        return new ResponseEntity<Product>(createdProduct, HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody Product product) {
        logger.info("Updating Product with id {}", id);

        Product currentProduct = productService.findById(id);
        if (currentProduct == null) {
            logger.error("Product with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentProduct.setId(product.getId());
        currentProduct.setName(product.getName());
        currentProduct.setDescription(product.getDescription());

        Product updatedProduct = productService.update(product);
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        logger.info("Fetching and deleting Product with id {}", id);

        if (productService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.delete(id);
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/products/")
    public ResponseEntity<?> deleteAllProducts() {
        logger.info("Deleting all users");

        productService.deleteAllProducts();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}