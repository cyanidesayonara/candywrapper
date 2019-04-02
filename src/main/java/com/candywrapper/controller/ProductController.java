package com.candywrapper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

import com.candywrapper.model.Product;
import com.candywrapper.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        model.addAttribute("view", "products");
        return "base";
    }

    @GetMapping("/{id}/")
    public String getProduct(@PathVariable("id") String id, Model model) {
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("view", "product");
        return "base";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("view", "new");
        return "base";
    }

    @PostMapping("/")
    public String postProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            logger.info("Creating Product : {}", product);
            if (bindingResult.hasErrors()) {
                // error message
                model.addAttribute("view", "new");
                return "base";
            }
    
            productService.save(product);
        }
        return "redirect:/products/";
    }    

    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable("id") String id, Model model) {
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("view", "edit");
        return "base";
    }

    @PutMapping("/{id}/")
    public String putProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            logger.info("Updating Product : {}", product);
    
            if (bindingResult.hasErrors()) {
                // error message
                model.addAttribute("view", "edit");
                return "base";
            }
    
            product.setId(product.getId());
            product.setName(product.getName());
            product.setDescription(product.getDescription());
    
            productService.update(product);
        }
        return "redirect:/products/";
    }    

    @DeleteMapping("/{id}/")
    public String deleteProduct(@PathVariable("id") String id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            productService.deleteById(id);
        }
        return "redirect:/products/";
    }
}