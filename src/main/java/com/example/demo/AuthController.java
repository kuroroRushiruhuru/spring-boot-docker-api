package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Map<String, String> login(
            @RequestBody LoginRequest request
    ) {

        // 仮ログイン
        if (
                "admin".equals(request.getUsername()) &&
                        "password".equals(request.getPassword())
        ) {

            String token =
                    jwtUtil.generateToken(request.getUsername());

            return Map.of("token", token);
        }

        throw new RuntimeException("ユーザー名またはパスワードが違います");
    }
}