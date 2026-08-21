package ao.com.laravel.biblioteca_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "🚀 API da Biblioteca funcionando com sucesso em Angola!";
    }
}