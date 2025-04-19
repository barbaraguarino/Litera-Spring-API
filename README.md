# LiteraSpring

LiteraSpring é uma API RESTful desenvolvida com Spring Boot para gerenciamento de livros. O sistema permite o controle de livros, autores, gêneros literários, tradutores, sagas literárias, entre outras informações, visando oferecer uma base sólida e escalável para aplicações de gerenciamento bibliotecário.

Este projeto é ideal para fins acadêmicos e práticos, ilustrando boas práticas de arquitetura, organização de código e utilização do ecossistema Spring.

## ⚙️ Tecnologias utilizadas
- Java 17+
- Spring Boot 3.4.4
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- IntelliJ IDEA (recomendado)

## 📦 Instalação e execução local

### Pré-requisitos
Antes de começar, você vai precisar ter instalado:
- Java JDK 17+
- Maven 3.8+ 
- Git
- (Opcional) IntelliJ IDEA ou outro IDE compatível com projetos Spring Boot

### Passo a passo
1. Clone o repositório
    ```bash
    git clone https://github.com/seu-usuario/literaspring.git
    cd literaspring
    ```
2. Configure o banco de dados PostgreSQL:

    Crie um banco de dados PostgreSQL local para a aplicação. Exemplo:
   ```bash
   psql -U postgres
   CREATE DATABASE literaspring;
    ```
3. Configure as credenciais do banco no arquivo `application.properties`:
   
    No arquivo `src/main/resources/application.properties`, configure o acesso ao PostgreSQL com as informações corretas:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/literaspring
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
    ```
4. Compile o projeto:
    ```bash
   mvn clean install
    ```
5. Execute a aplicação:
    ```bash
   mvn spring-boot:run
    ```
   Ou diretamente pelo IntelliJ IDEA clicando no botão de run na classe principal `LiteraSpringApplication`.


6. Acesse o sistema:

    A aplicação estará disponível em: http://localhost:8080

## 📚 Objetivos do projeto
Este projeto tem como objetivos principais:
- Servir como base para estudo e prática com Spring Boot 
- Demonstrar arquitetura limpa e modular 
- Aplicar conceitos de Engenharia de Software (design, documentação, testes)