package com.sd133.SpringSecurityDemo.service;

import com.sd133.SpringSecurityDemo.model.Users;
import com.sd133.SpringSecurityDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    public Users registerUser(Users user) {
        return repo.save(user);
    }


    public String validateUser(Users user) {
        try {
            Authentication authentication = authManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            return (authentication.isAuthenticated()) ? jwtService.generateToken(user.getUsername()) : "Authentication Failed";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
