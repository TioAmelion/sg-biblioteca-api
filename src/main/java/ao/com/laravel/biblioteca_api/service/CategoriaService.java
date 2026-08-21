package ao.com.laravel.biblioteca_api.service;

import ao.com.laravel.biblioteca_api.dto.request.CategoriaRequest;
import ao.com.laravel.biblioteca_api.dto.response.CategoriaResponse;
import ao.com.laravel.biblioteca_api.entity.Categoria;
import ao.com.laravel.biblioteca_api.exception.LivroNaoEncontradoException;
import ao.com.laravel.biblioteca_api.exception.RegraNegocioException;
import ao.com.laravel.biblioteca_api.mapper.CategoriaMapper;
import ao.com.laravel.biblioteca_api.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CategoriaService {
    private final CategoriaRepository repository;

    @Transactional
    public CategoriaResponse create(CategoriaRequest request) {
        if (repository.existsByNome(request.getNome())) {
            throw new RegraNegocioException("Nome da categoria já cadastrado");
        }

        Categoria categoria = CategoriaMapper.toEntity((request));
        Categoria salvo = repository.save(categoria);
        return CategoriaMapper.toResponse(salvo);
    }

    public List<CategoriaResponse> list() {
        return repository.findAll()
                .stream()
                .map(CategoriaMapper::toResponse)
                .toList();
    }

    public CategoriaResponse findById(Long id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException("Categoria não encontrado."));

        return CategoriaMapper.toResponse(categoria);
    }

    @Transactional
    public CategoriaResponse update(Long id, CategoriaRequest request) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException("Categoria não encontrado."));

        Optional<Categoria> categoriaExistente = repository.findByNome(request.getNome());

        if (categoriaExistente.isPresent() && !categoriaExistente.get().getId().equals(id)) {
            throw new RegraNegocioException("Já existe uma categoria com esse Nome.");
        }

        categoria.setNome(request.getNome());
        categoria.setDescricao(request.getDescricao());

        Categoria atualizado = repository.save(categoria);

        return CategoriaMapper.toResponse(atualizado);
    }

    @Transactional
    public void delete(Long id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException("Categoria não encontrado."));

        repository.delete(categoria);
    }
}
