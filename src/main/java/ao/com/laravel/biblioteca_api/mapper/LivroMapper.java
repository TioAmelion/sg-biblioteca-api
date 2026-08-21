package ao.com.laravel.biblioteca_api.mapper;

import ao.com.laravel.biblioteca_api.dto.request.LivroRequest;
import ao.com.laravel.biblioteca_api.dto.response.LivroResponse;
import ao.com.laravel.biblioteca_api.entity.Categoria;
import ao.com.laravel.biblioteca_api.entity.Livro;

public class LivroMapper {
    private LivroMapper() {}

    public static Livro toEntity(LivroRequest request) {
        Categoria categoria = Categoria.builder()
                .id(request.getCategoriaId())
                .build();

        return Livro.builder()
                .titulo(request.getTitulo())
                .autor(request.getAutor())
                .isbn(request.getIsbn())
                .anoPublicacao(request.getAnoPublicacao())
                .qtd(request.getQtd())
                .categoria(categoria)
                .build();
    }

    public static LivroResponse toResponse(Livro livro) {
        return LivroResponse.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .autor(livro.getAutor())
                .isbn(livro.getIsbn())
                .anoPublicacao(livro.getAnoPublicacao())
                .qtd(livro.getQtd())
                .dataCadastro(livro.getDataCadastro())
                .categoriaId(livro.getCategoria().getId())
                .build();
    }
}
