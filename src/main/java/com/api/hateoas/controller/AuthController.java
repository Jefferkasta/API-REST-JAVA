package com.api.hateoas.controller;
import com.api.hateoas.dto.LoginDto;
import com.api.hateoas.dto.RegisterDto;
import com.api.hateoas.service.AuthService;
import org.antlr.v4.runtime.misc.NotNull;
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

    /*@PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto){
        LoginDto resultLog = authService.login(LoginDto loginDto);
        return new ResponseEntity<>("Login exitoso", HttpStatus.OK);
    }*/


    @PostMapping("/login")
    public void login(@RequestParam(value = "email") String email,
                      @RequestParam(value = "password") String password) {
    }
}