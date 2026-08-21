package ao.com.laravel.biblioteca_api.repository;

import ao.com.laravel.biblioteca_api.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNome(String nome);

    boolean existsByNome(String descricao);
}
