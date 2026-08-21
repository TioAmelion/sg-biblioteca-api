package ao.com.laravel.biblioteca_api.mapper;

import ao.com.laravel.biblioteca_api.dto.request.CategoriaRequest;
import ao.com.laravel.biblioteca_api.dto.response.CategoriaResponse;
import ao.com.laravel.biblioteca_api.entity.Categoria;

public class CategoriaMapper {
    private CategoriaMapper() {}

    public static Categoria toEntity(CategoriaRequest request) {
        return Categoria.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .build();
    }

    public static CategoriaResponse toResponse(Categoria categoria) {
        return CategoriaResponse.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .descricao(categoria.getDescricao())
                .build();
    }
}
