package com.sd133.SpringSecurityDemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name="userdetails")
public class Users {
    @Id
    private int id;
    private String username;
    private String password;
}
