package ao.com.laravel.biblioteca_api.service;

import ao.com.laravel.biblioteca_api.security.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class UsuarioService {
    private final SecurityConfig passwordEncoder;
}
