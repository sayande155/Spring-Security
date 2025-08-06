package com.sd133.SpringSecurityDemo.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private int roll;
    private String name;
    private String resultStatus;
}
