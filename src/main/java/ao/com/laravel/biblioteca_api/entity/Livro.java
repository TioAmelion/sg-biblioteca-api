package ao.com.laravel.biblioteca_api.entity;

import  jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "livros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Livro {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(unique = true, nullable = false)
    private String isbn;

    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;

    private Integer qtd;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @PrePersist
    public  void prePersist() {
        this.dataCadastro = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)

    private Categoria categoria;

//    fetch = FetchType.LAZY: Recomendado por performance. O Hibernate só vai carregar os dados da Categoria quando chamares explicitamente livro.getCategoria().getNome(), evitando consultas desnecessárias ao banco.
//
//    nullable = false na @JoinColumn: Garante que nenhum livro seja guardado no banco de dados sem estar associado a uma categoria.
}
