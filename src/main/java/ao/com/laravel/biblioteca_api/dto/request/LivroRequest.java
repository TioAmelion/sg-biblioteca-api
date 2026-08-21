package ao.com.laravel.biblioteca_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class LivroRequest {
    @NotBlank(message = "O titulo é obrigatório")
    private String titulo;

    @NotBlank(message = "O autor é obrigatório.")
    private String autor;

    @NotBlank(message = "O ISBN é obrigatório.")
    private String isbn;

    @NotNull(message = "O ano de publicação é obrigatório.")
    private Integer anoPublicacao;

    @NotNull(message = "A quantidade é obrigatória.")
    @Positive(message = "A quantidade deve ser maior que zero.")
    private Integer qtd;

    @NotNull(message = "A categoria é obrigatória")
    private Long categoriaId;
}
