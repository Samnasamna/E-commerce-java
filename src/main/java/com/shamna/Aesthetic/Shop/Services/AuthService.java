package com.shamna.Aesthetic.Shop.Services;

import com.shamna.Aesthetic.Shop.Entities.AuthResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shamna.Aesthetic.Shop.Entities.Login;
import com.shamna.Aesthetic.Shop.Repositories.LoginRepository;

@Service
public class AuthService {
    private final LoginRepository repo;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public AuthService(LoginRepository repo, AuthenticationManager authenticationManager, JwtService jwtService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repo = repo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public AuthResponse checkUser(Login user) {
        try{
            System.out.println("Attempting to authenticate user: " + user.getUname());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUname(),user.getPass()
                    )
            );

            if(authentication.isAuthenticated()){
                System.out.println("Authentication successful for user: " + user.getUname());
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                String role = userDetails.getAuthorities().iterator().next().getAuthority();
                System.out.println("Role of the user is "+role);
                String token =  jwtService.generateToken(user);
                return new AuthResponse(token,role);
            }

        }catch(BadCredentialsException e){
            System.out.println("Authentication failed for user: " +  user.getUname());
            throw new BadCredentialsException("check your username or password and try again!");
        }catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
            throw new RuntimeException("Something went wrong");
        }
        return null;
    }


    public AuthResponse addUser(Login data) {
        try{
            System.out.println("from the signup "+data.getUname());
            if(repo.findByUname(data.getUname()).isEmpty()){
                System.out.println("saving the credentialss..... ");
                data.setPass(bCryptPasswordEncoder.encode(data.getPass()));
                data.setRole("USER");
                repo.save(data);
                String token =  jwtService.generateToken(data);
                return new AuthResponse(token,data.getRole());
            }
        }catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
            throw new RuntimeException("Something went wrong");
        }
        return null;

    }


}
