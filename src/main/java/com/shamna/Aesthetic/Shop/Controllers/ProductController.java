package com.shamna.Aesthetic.Shop.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.shamna.Aesthetic.Shop.Entities.ProductDetails;
import com.shamna.Aesthetic.Shop.Services.ProductService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping("/{id}")
    public ProductDetails getProductById(@PathVariable int id) {
        return service.getProductById(id);
    }

    @GetMapping
    public List<ProductDetails> searchProducts(@RequestParam(required = false) String search) {
        return search == null ? service.getProducts() : service.searchProducts(search) ;
    }
    

    

    

    
    
    
    
    
    
}
