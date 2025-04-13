package com.shamna.Aesthetic.Shop.Entities;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;


@Entity
public class CartData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int cartId;
    private String userId;
    private int pid;
    private int quantity = 1;

    public CartData() {
    }

    public CartData(int cartId, int quantity, int pid, String userId) {
        this.cartId = cartId;
        this.quantity = quantity;
        this.pid = pid;
        this.userId = userId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
