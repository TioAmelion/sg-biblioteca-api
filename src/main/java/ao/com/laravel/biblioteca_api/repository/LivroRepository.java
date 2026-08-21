package ao.com.laravel.biblioteca_api.repository;

import ao.com.laravel.biblioteca_api.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);
}
