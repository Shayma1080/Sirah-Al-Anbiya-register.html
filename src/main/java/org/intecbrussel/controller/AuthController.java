package org.intecbrussel.controller;

import org.intecbrussel.dto.LoginRequest;
import org.intecbrussel.dto.RegisterRequest;
import org.intecbrussel.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.intecbrussel.model.User;
import org.intecbrussel.service.AuthService;
import org.intecbrussel.service.LoginService;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final LoginService loginService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return authService.register(request.getUsername(), request.getEmail(), request.getPassword());
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        String jwt = loginService.login(request.getUsername(), request.getPassword());
        return new TokenResponse(jwt);
    }
}
