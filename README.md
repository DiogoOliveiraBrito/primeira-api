# Primeira API REST com Spring Boot

Este é um projeto de exemplo de uma API REST para gerenciamento de pessoas, construída com Spring Boot.

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação.
- **Spring Boot 3.x**: Framework para construção da API.
- **Spring Data JPA**: Para a persistência de dados e interação com o banco.
- **Maven**: Gerenciador de dependências.
- **Lombok**: Para simplificar o código com a geração de getters e setters.
- **Hibernate Validator**: Para validação de dados com anotações (`@Valid`, `@NotBlank`, etc.).
- **PostgreSQL**: Banco de dados relacional.
- **OpenAPI / Swagger**: Para a documentação interativa da API.

## Configuração e Como Rodar o Projeto

Este projeto utiliza o PostgreSQL. Siga os passos abaixo para configurá-lo.

1.  **Clone o repositório**:
    ```bash
    git clone [https://github.com/DiogoOliveiraBrito/primeira-api.git](https://github.com/DiogoOliveiraBrito/primeira-api.git)
    ```

2.  **Crie o banco de dados**:
    * Certifique-se de que o PostgreSQL está instalado e rodando.
    * Crie o banco de dados com o nome `spring` (ou o nome que você definiu no `application.properties`).

3.  **Configure o arquivo de propriedades**:
    * Este projeto utiliza um arquivo de modelo (`application.properties.example`) para gerenciar as configurações.
    * Crie uma cópia deste arquivo e renomeie-a para **`application.properties`**.
    * **Não adicione o arquivo `application.properties` no Git.** O arquivo `.gitignore` já está configurado para ignorá-lo.

4.  **Defina as variáveis de ambiente**:
    * Suas credenciais de login devem ser armazenadas em variáveis de ambiente por segurança.
    * No seu terminal, defina `DB_USERNAME` e `DB_PASSWORD` com suas credenciais do PostgreSQL.

    * **No Windows (CMD):**
      ```bash
      set DB_USERNAME=seu_usuario_do_postgres
      set DB_PASSWORD=sua_senha_do_postgres
      ```
 

5.  **Rode a aplicação**:
    * Abra o projeto em sua IDE (IntelliJ, VS Code).
    * Execute a classe principal da aplicação.
    * A API estará disponível em `http://localhost:8080/`.

## Endpoints da API

- **POST /pessoas**: Cria uma nova pessoa.
- **GET /pessoas**: Busca todas as pessoas cadastradas.
- **GET /pessoas/{id}**: Busca uma pessoa por ID.
- **PUT /pessoas/{id}**: Atualiza uma pessoa existente.
- **DELETE /pessoas/{id}**: Deleta uma pessoa por ID.