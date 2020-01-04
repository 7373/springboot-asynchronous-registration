package com.example.demo.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String uuid;

    //... getter and setter
}
