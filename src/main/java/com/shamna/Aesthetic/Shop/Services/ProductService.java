package com.shamna.Aesthetic.Shop.Services;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shamna.Aesthetic.Shop.Entities.ProductDetails;
import com.shamna.Aesthetic.Shop.Repositories.ProductRepository;



@Service
public class ProductService {
    HashMap<Integer,Integer> map = new HashMap<>();

    @Autowired
    ProductRepository repo;


    public List<ProductDetails> getProducts(){
        return repo.findAll();
    }

    public ProductDetails getProductById(int id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }
    public List<ProductDetails> searchProducts(String search) {
        return repo.searchProducts(search);
    }


    

}
