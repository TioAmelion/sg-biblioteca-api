package ao.com.laravel.biblioteca_api.controller;

import ao.com.laravel.biblioteca_api.dto.request.CategoriaRequest;
import ao.com.laravel.biblioteca_api.dto.response.CategoriaResponse;
import ao.com.laravel.biblioteca_api.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/categoria")
@RequiredArgsConstructor

public class CategoriaController {
    private final CategoriaService service;

    @PostMapping("/create")
    public ResponseEntity<CategoriaResponse> crete(@RequestBody @Valid CategoriaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> findById() {
        return  ResponseEntity.ok(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> findById(@PathVariable Long id) {
        return  ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid CategoriaRequest request) {

        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
