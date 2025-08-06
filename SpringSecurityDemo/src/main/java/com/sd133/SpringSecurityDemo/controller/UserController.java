package com.sd133.SpringSecurityDemo.controller;

import com.sd133.SpringSecurityDemo.model.Users;
import com.sd133.SpringSecurityDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
public class UserController {

    @Autowired
    private UserService service;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return new ResponseEntity<>(service.registerUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> validateUser(@RequestBody Users user) {
        String msg = service.validateUser(user);
        if (msg.equals("Bad credentials")) {
            System.out.println(msg);
            return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);
        }
        else{
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }
    }
}
