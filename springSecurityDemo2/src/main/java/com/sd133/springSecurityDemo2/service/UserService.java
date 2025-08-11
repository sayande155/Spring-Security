package com.sd133.springSecurityDemo2.service;

import com.sd133.springSecurityDemo2.model.User;
import com.sd133.springSecurityDemo2.model.UserLoginDto;
import com.sd133.springSecurityDemo2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<String> doRegister(User user) {
        if (userRepository.existsById(user.getUsername())) {
            System.out.println("User Exists");
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        }

        user.setPassword(encoder.encode(user.getPassword()));

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>("Registration Success", HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> doUpdate(User user) {
        try {
            if (userRepository.existsById(user.getUsername())) {
                userRepository.save(user);
                return new ResponseEntity<>("Update Successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not exist", HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<String> doValidate(UserLoginDto userLoginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));
            return authentication.isAuthenticated() ?
                    new ResponseEntity<>("Login Success", HttpStatus.OK) :
                    new ResponseEntity<>("Login Failed", HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}