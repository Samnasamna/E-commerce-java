package com.shamna.Aesthetic.Shop.Services;

import com.shamna.Aesthetic.Shop.Entities.Login;
import com.shamna.Aesthetic.Shop.Repositories.LoginRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ProfileService {
    private final HttpServletRequest request;
    private final JwtService jwtService;
    private final LoginRepository loginRepository;

    public ProfileService(HttpServletRequest request, JwtService jwtService, LoginRepository loginRepository) {
        this.request = request;
        this.jwtService = jwtService;
        this.loginRepository = loginRepository;
    }

    public Optional<Login> getProfile(){

        String token = request.getHeader("Authorization").substring(7);
        String username = jwtService.extractUsername(token);
        return loginRepository.findByUname(username);
    }

    @Transactional
    public Optional<Login> editProfile(@RequestBody Login user) {
        return loginRepository.findById(user.getUserId())
                .map(existingUser -> {
                    existingUser.setFirstName(user.getFirstName());
                    existingUser.setLastName(user.getLastName());
                    existingUser.setUname(user.getUname());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setPhone(user.getPhone());
                    return loginRepository.save(existingUser);
                });

    }
}
