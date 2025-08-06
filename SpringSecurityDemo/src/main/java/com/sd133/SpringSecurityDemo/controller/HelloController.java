package com.sd133.SpringSecurityDemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
public class HelloController {

    @GetMapping("/")
    public String greet(HttpServletRequest request) {
        long epochMilli = request.getSession().getCreationTime();
        Instant instant = Instant.ofEpochMilli(epochMilli);
        LocalDateTime time = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return "Hello World    \n" + request.getSession().getId() + "   \n   " + time;
    }
}
