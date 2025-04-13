package com.shamna.Aesthetic.Shop.Controllers;

import com.shamna.Aesthetic.Shop.Entities.Address;
import com.shamna.Aesthetic.Shop.Entities.OrderData;
import com.shamna.Aesthetic.Shop.Services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/order/address")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Optional<Address> getAddress(){
        return orderService.getAddress();
    }

    @PutMapping
    public Optional<Address> updateAddress(@RequestBody Address address){
        return orderService.updateAddress(address);
    }

    @GetMapping("/buyNow")
    public Optional<OrderData> getOrderDetails(){
        return orderService.getOrderDetails();
    }

    @PostMapping("/buyNow")
    public Optional<OrderData> addOrderData(@RequestBody OrderData data){
        return orderService.addOrderData(data);
    }
}
