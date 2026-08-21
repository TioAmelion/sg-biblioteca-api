package ao.com.laravel.biblioteca_api.service;

import ao.com.laravel.biblioteca_api.dto.request.LivroRequest;
import ao.com.laravel.biblioteca_api.dto.response.LivroResponse;
import ao.com.laravel.biblioteca_api.entity.Livro;
import ao.com.laravel.biblioteca_api.exception.LivroNaoEncontradoException;
import ao.com.laravel.biblioteca_api.exception.RegraNegocioException;
import ao.com.laravel.biblioteca_api.mapper.LivroMapper;
import ao.com.laravel.biblioteca_api.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class LivroService {
    private static final Logger log = LoggerFactory.getLogger(LivroService.class);
    private  final LivroRepository repository;

    @CacheEvict(value = "livros", allEntries = true)
    @Transactional
    public LivroResponse create(LivroRequest request) {

        log.info("Iniciando cadastro do livro '{}'.", request.getTitulo());

        if(repository.existsByIsbn(request.getIsbn())) {
            log.warn("Tentativa de cadastrar ISBN duplicado: {}", request.getIsbn());
            throw new RegraNegocioException("ISBN já cadastrado");
        }

        Livro livro = LivroMapper.toEntity((request));
        Livro salvo = repository.save(livro);

        log.info("Livro cadastrado com ID {}.", salvo.getId());

        return LivroMapper.toResponse(salvo);
    }

    @Cacheable("livros")
    public List<LivroResponse> list() {
        return repository.findAll()
                .stream()
                .map(LivroMapper::toResponse)
                .toList();
    }

    @Cacheable(value = "livros", key = "#id")
    public LivroResponse findById(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException("Livro não encontrado."));

        return LivroMapper.toResponse(livro);
    }

    @Caching(
        put = {
                @CachePut(value = "livros", key = "#id")
        },
        evict = {
                @CacheEvict(value="livros", allEntries= true)
        }
    )
    @Transactional
    public LivroResponse update(Long id, LivroRequest request) {
        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException("Livro não encontrado."));

        Optional<Livro> livroExistente = repository.findByIsbn(request.getIsbn());

        if (livroExistente.isPresent() && !livroExistente.get().getId().equals(id)) {
            throw new RegraNegocioException("Já existe um livro com este ISBN.");
        }

        livro.setTitulo(request.getTitulo());
        livro.setAutor(request.getAutor());
        livro.setIsbn(request.getIsbn());
        livro.setAnoPublicacao(request.getAnoPublicacao());
        livro.setQtd(request.getQtd());

        Livro atualizado = repository.save(livro);

        return LivroMapper.toResponse(atualizado);
    }

    @Caching(
        evict = {
                @CacheEvict(value = "livros", key = "#id"),
                @CacheEvict(value = "livros", allEntries = true)
        }
    )
    @Transactional
    public void delete(Long id) {

        Livro livro = repository.findById(id)
                .orElseThrow(() ->
                        new LivroNaoEncontradoException("Livro não encontrado."));

        repository.delete(livro);
    }
}
