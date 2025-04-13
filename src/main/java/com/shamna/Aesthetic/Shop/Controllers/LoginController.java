package com.shamna.Aesthetic.Shop.Controllers;

import com.shamna.Aesthetic.Shop.Entities.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shamna.Aesthetic.Shop.Entities.Login;
import com.shamna.Aesthetic.Shop.Services.AuthService;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    AuthService service;

    @PostMapping("/login")
    public AuthResponse checkUser(@RequestBody Login user) {
        return service.checkUser(user);
    }
    
    @PostMapping("/signup")
    public AuthResponse addUser(@RequestBody Login data) {
        System.out.println("register new user "+ data.getUname());
        return service.addUser(data);
    }


}
