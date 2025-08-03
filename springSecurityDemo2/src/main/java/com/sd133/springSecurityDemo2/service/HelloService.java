package com.sd133.springSecurityDemo2.service;

import com.sd133.springSecurityDemo2.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public ResponseEntity<String> greet() {
        return new ResponseEntity<>("Hello User", HttpStatus.OK);
    }

    public ResponseEntity<Employee> showEmployee(Employee employee) {
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
