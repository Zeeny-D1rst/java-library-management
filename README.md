# Java Library Management

Aplicação de console para gerenciamento de um pequeno acervo. O projeto aplica orientação a objetos, encapsulamento, separação de responsabilidades e validações de regras de negócio.

## Funcionalidades
- Cadastrar livros com ISBN, título e autor.
- Listar e pesquisar livros.
- Emprestar e devolver livros com validação de disponibilidade.
- Evitar ISBN duplicado e empréstimos de itens já emprestados.

## Requisitos e execução
Java 17+ e Maven 3.8+.
```bash
mvn test
mvn package
java -jar target/library-management-1.0.0.jar
```
