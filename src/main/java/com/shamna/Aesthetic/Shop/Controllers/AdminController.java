package com.shamna.Aesthetic.Shop.Controllers;

import com.shamna.Aesthetic.Shop.Entities.Login;
import com.shamna.Aesthetic.Shop.Entities.ProductDetails;
import com.shamna.Aesthetic.Shop.Services.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/addProduct")
    public Optional<ProductDetails> addProducts(@RequestBody ProductDetails product){
        return adminService.addProducts(product);
    }

    @PutMapping("/updateProduct")
    public Optional<ProductDetails> updateProducts(@RequestBody ProductDetails product){
        return adminService.updateProducts(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id){
        return adminService.deleteProduct(id);
    }

    @GetMapping("/getProducts")
    public List<Login> getProducts(){
        return adminService.getProducts();
    }

}
