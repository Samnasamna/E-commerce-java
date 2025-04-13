package com.shamna.Aesthetic.Shop.Services;

import com.shamna.Aesthetic.Shop.Entities.CustomUserDetails;
import com.shamna.Aesthetic.Shop.Entities.Login;
import com.shamna.Aesthetic.Shop.Repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService  implements UserDetailsService {
    @Autowired
    private LoginRepository repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //3
        System.out.println("Received username: " + username);
        if (username == null || username.isEmpty()) {
            System.out.println("Username is null or empty");
            throw new UsernameNotFoundException("Username is null or empty");
        }

        Login user = repo.findByUname(username)
                .orElseThrow(() -> {
                    System.out.println("User not found for the username: " + username);
                    return new UsernameNotFoundException("User not found for the username " + username);
                });

        //4
        System.out.println("User found: " + user.getUname());
        return new CustomUserDetails(user);
    }
}
