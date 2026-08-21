package ao.com.laravel.biblioteca_api.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.io.Serializable;

@Data
@Builder

public class LivroResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private Integer anoPublicacao;
    private Integer qtd;
    private Long categoriaId;
    private LocalDateTime dataCadastro;
}
