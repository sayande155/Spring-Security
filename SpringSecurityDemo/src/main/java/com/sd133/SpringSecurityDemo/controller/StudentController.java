package com.sd133.SpringSecurityDemo.controller;

import com.sd133.SpringSecurityDemo.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    static List<Student> students = new ArrayList<>(Arrays.asList(new Student(1, "Rohit Sharma", "P"), new Student(2, "Virat Kohli", "XP"), new Student(3, "Karun Nair", "F")));

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<HttpStatus> setStudents(@RequestBody Student s) {
//        System.out.println(s);
        students.add(s);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @GetMapping("/csrf-token")
//    public CsrfToken getCsrfToken(HttpServletRequest request) {
//        return (CsrfToken) request.getAttribute("_csrf");
//    }
}
