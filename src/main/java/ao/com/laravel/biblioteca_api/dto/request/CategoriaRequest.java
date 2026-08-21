package ao.com.laravel.biblioteca_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class CategoriaRequest {
    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nome;

    @NotBlank(message = "A descricao da categoria é obrigatório")
    private String descricao;
}
