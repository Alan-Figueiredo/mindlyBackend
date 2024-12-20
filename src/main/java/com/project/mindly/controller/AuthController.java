package com.project.mindly.controller;

import com.project.mindly.config.AuthenticationException;
import com.project.mindly.dtos.userAuth.AuthResponse;
import com.project.mindly.dtos.userAuth.UserAuth;
import com.project.mindly.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsers(@RequestBody @Valid UserAuth data) {
        try {
            AuthResponse authResponse = authService.authenticateUser(data.email(), data.password());
            return ResponseEntity.status(HttpStatus.OK).body(authResponse);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }
}
