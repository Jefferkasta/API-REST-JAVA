package com.api.hateoas.controller;

import com.api.hateoas.dto.RegisterDto;
import com.api.hateoas.model.Cuenta;
import com.api.hateoas.model.UserModel;
import com.api.hateoas.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDto registerDto) {
        RegisterDto result = authService.save(registerDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public void login(@RequestParam(value = "email") String email,
                      @RequestParam(value = "password") String password) {
    }
}