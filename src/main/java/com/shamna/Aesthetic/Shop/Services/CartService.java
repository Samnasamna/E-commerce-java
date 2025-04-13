package com.shamna.Aesthetic.Shop.Services;

import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shamna.Aesthetic.Shop.Entities.CartData;
import com.shamna.Aesthetic.Shop.Entities.ProductDetails;
import com.shamna.Aesthetic.Shop.Repositories.CartRepository;
import com.shamna.Aesthetic.Shop.Repositories.ProductRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {
    private final JwtService jwtService;


    private final HttpServletRequest request;
    private final CartRepository cartRepo;

    public CartService(JwtService jwtService, HttpServletRequest request, CartRepository cartRepo) {
        this.jwtService = jwtService;
        this.request = request;
        this.cartRepo = cartRepo;
    }

    @Transactional //if the changes has to be updated or reflected in the database
    public void addToCart(CartData data) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);

        CartData cart = cartRepo.findByUserIdAndPid(username, data.getPid()).orElse(null);
        if (cart == null) {
            cart = new CartData();
            cart.setUserId(username);
            cart.setPid(data.getPid());
            cart.setQuantity(data.getQuantity() > 0 ? data.getQuantity() : 1);
        } else {
            cart.setQuantity(cart.getQuantity() + data.getQuantity());
        }
        cartRepo.save(cart);


    }


    public List<CartData> getCartData() {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        System.out.println("finding ............. ");
        return cartRepo.findAllByUserId(username);
    }

    @Transactional
    public void deleteCartData(int pid){
        System.out.println("id value is "+ pid);
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        cartRepo.deleteByUserIdAndPid(username,pid);
    }

    @Transactional
    public void updateCart(CartData data) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        CartData cart = cartRepo.findByUserIdAndPid(username, data.getPid()).orElse(null);
        if (cart != null) {
            cart.setQuantity(data.getQuantity());
            cartRepo.save(cart);
        } else {
            throw new RuntimeException("Cart item not found");
        }
    }
}
