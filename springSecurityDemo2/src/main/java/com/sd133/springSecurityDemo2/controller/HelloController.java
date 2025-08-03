package com.sd133.springSecurityDemo2.controller;

import com.sd133.springSecurityDemo2.model.Employee;
import com.sd133.springSecurityDemo2.service.HelloService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping
    public ResponseEntity<String> greet() {
        return helloService.greet();
    }

    @GetMapping("x-csrf")
    public ResponseEntity<CsrfToken> getCsrfToken(HttpServletRequest request) {
        return new ResponseEntity<>((CsrfToken) request.getAttribute("_csrf"), HttpStatus.OK);
    }

    @PostMapping("user")
    public ResponseEntity<Employee> showEmployee(@RequestBody Employee employee) {
        System.out.println(employee);
        return helloService.showEmployee(employee);
    }
}
