package com.shamna.Aesthetic.Shop.Controllers;

import com.shamna.Aesthetic.Shop.Entities.Login;
import com.shamna.Aesthetic.Shop.Services.ProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public Optional<Login> getProfile(){
        return profileService.getProfile();

    }

    @PutMapping
    public Optional<Login> editProfile(@RequestBody Login user){
        return profileService.editProfile(user);

    }
}
