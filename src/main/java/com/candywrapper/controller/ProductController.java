package com.candywrapper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.candywrapper.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
    
    public static final Logger logger = LoggerFactory.getLogger(ProductAPIController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String getProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/{id}/")
    public String getProduct(@PathVariable("id") String id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product";
    }

    @DeleteMapping("/{id}/")
    public String deleteProduct(@PathVariable("id") String id, Model model) {
        productService.deleteById(id);
        return "redirect:/products/";
    }    
}