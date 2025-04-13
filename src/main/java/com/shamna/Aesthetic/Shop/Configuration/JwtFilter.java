package com.shamna.Aesthetic.Shop.Configuration;

import com.shamna.Aesthetic.Shop.Services.CustomUserDetailsService;
import com.shamna.Aesthetic.Shop.Services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final ApplicationContext context;

    public JwtFilter(JwtService jwtService, ApplicationContext context) {
        this.jwtService = jwtService;
        this.context = context;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = "";
        String username = "";

        

        if(authHeader!=null && authHeader.startsWith("Bearer")){
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }
        else if(authHeader!=null && authHeader.equals("ADMIN")){
            filterChain.doFilter(request, response);
            return;
        }
        else{
            if (request.getRequestURI().equals("/auth/login") || request.getRequestURI().equals("/auth/signup") || request.getRequestURI().startsWith("/api/products") ) {
                filterChain.doFilter(request, response);
                return;
            }
        }


        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = context.getBean(CustomUserDetailsService.class).loadUserByUsername(username);

            if(jwtService.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
