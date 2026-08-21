package ao.com.laravel.biblioteca_api.controller;

import ao.com.laravel.biblioteca_api.dto.request.LoginRequest;
import ao.com.laravel.biblioteca_api.dto.response.LoginResponse;
import ao.com.laravel.biblioteca_api.security.JwtService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody @Valid LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getSenha()
                        )
                );

        String token = jwtService.generateToken(
                (org.springframework.security.core.userdetails.UserDetails)
                        authentication.getPrincipal());
        return new LoginResponse(token);
    }
}
