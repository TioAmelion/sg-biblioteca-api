CREATE TABLE livros (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
titulo VARCHAR(255) NOT NULL,
autor VARCHAR(255) NOT NULL,
isbn VARCHAR(50) NOT NULL UNIQUE,
ano_publicacao INT,
qtd INT,
data_cadastro TIMESTAMP NOT NULL,
categoria_id BIGINT,
FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);