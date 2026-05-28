package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public boolean login(String username, String password) {
        return "admin".equals(username) && "password".equals(password);
    }
}