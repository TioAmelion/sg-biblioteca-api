package ao.com.laravel.biblioteca_api.controller;

import ao.com.laravel.biblioteca_api.dto.request.LivroRequest;
import ao.com.laravel.biblioteca_api.dto.response.LivroResponse;
import ao.com.laravel.biblioteca_api.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/livros")
@RequiredArgsConstructor

public class LivroController {
    private  final LivroService service;

    @Value("${HOSTNAME:Instancia-padrao}")
    private String instance;

    @PostMapping("/create")
    public ResponseEntity<LivroResponse> create(
            @RequestBody @Valid LivroRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<LivroResponse>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid LivroRequest request) {

        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/instance")
    public String instance() {
        return "Requisição respondida pela instância: " + instance;
    }

}
