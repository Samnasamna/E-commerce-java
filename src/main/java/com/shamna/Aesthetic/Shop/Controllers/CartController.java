package com.shamna.Aesthetic.Shop.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.shamna.Aesthetic.Shop.Entities.CartData;
import com.shamna.Aesthetic.Shop.Services.CartService;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    CartService service;

    @PostMapping("/cart")
    public void addToCart(@RequestBody CartData data){
        service.addToCart(data);
    }

    @PutMapping("/cart")
    public void updateCart(@RequestBody CartData data){
        service.updateCart(data);
    }
    @GetMapping("/cart")
    public List<CartData> getMethodName() {
        System.out.println("getting he cart data......");
        return service.getCartData();
    }


    @DeleteMapping("/cart/{id}")
    public void deleteCartData(@PathVariable int id){
        System.out.println("id value is "+ id);
        service.deleteCartData(id);
    }
}
