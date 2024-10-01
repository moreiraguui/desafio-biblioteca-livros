<h1 align="center">
  Biblioteca de Livros
</h1>

API para a criação, alteração, exclusão e listagem de livros.

## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [Mongo DB](https://www.mongodb.com/pt-br/docs/)

## Práticas adotadas

- API REST
- Consultas com Hibernate e Spring Data MongoDB
- Injeção de Dependências
- Geração automática do Swagger com a OpenAPI 3
- Teste Unitários com JUnit e Mockito.
- **Design Pattern Strategy e Factory na branch -> feature-implementando-strategy-factory
  
## Executar com IDE, Java e Maven

- Clonar repositório git
- Construir o projeto:
```
$ ./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/api-biblioteca-livros-0.0.1-SNAPSHOT.jar
```
- Ou com sua IDE preferida, fazer o build manualmente. Lembre-se: nos processos acima, será necessário a instalação de jdk, maven e MongoDB

## Executar com Docker:
  - A aplicação pode ser facilmente executada em um contêiner Docker. Para isso, siga os passos abaixo:

1. Certifique-se de ter o Docker e o Docker Compose instalados. Você pode baixar e instalar o Docker Desktop em [Docker Desktop](docker.com)

2. Construir e executar os contêineres:
    - Na raiz do projeto, onde os arquivos Dockerfile e docker-compose.yml estão localizados, execute o seguinte comando:

```
docker-compose up --build
```
#### Com isso, esse comando vai:
  - Construir a imagem da sua aplicação.
  - Iniciar o contêiner da aplicação junto com o contêiner do MongoDB.

### A API poderá ser acessada em [localhost:8080](http://localhost:8080) e o Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/):

- Cadastrar um livro em sua biblioteca.
```
POST -> http://localhost:8080/biblioteca/livros

{
    "titulo": "Moby Dick",
    "autor": "Herman Melville",
    "anoPublicacao": 1851,
    "isbn": "978-1503280786"
}
```
![image](https://github.com/user-attachments/assets/2928d61b-9d02-4199-a380-46ba2bcf8219)

- Listar todos os livros cadastrados de forma paginada.
  - A aplicação permite buscar todos os livros ao não passar nenhum param na requisição.
```
GET -> http://localhost:8080/biblioteca/livros
```
![image](https://github.com/user-attachments/assets/c34b78dc-280a-456b-bb14-82538bbac384)

- Listar livros pelo titulo de forma paginada.
  - A aplicação permite buscar livros pelo título, ignorando maiúsculas e minúsculas e permitindo buscas parciais. Por exemplo, ao buscar por "rev", o livro "A Revolução dos Bichos" será encontrado.
```
GET -> http://localhost:8080/biblioteca/livros?titulo=<titulo>
```
![image](https://github.com/user-attachments/assets/af43ff79-987d-4d07-be7f-63bdfa27cb51)

- Listar livros pelo autor de forma paginada.
  - A aplicação permite buscar livros pelo autor, ignorando maiúsculas e minúsculas e permitindo buscas parciais. Por exemplo, ao buscar por "assis", o livro "Dom Casmurro" será encontrado.
```
GET -> http://localhost:8080/biblioteca/livros?autor=<autor>
```
![image](https://github.com/user-attachments/assets/49a41b47-37df-48aa-ba85-b676fc3d7035)

- Listar livros pelo ano de publicação de forma paginada.
  - A aplicação permite buscar livros pelo ano de publicacao.
```
GET -> http://localhost:8080/biblioteca/livros?anoPublicacao=<anoPublicacao>
```
![image](https://github.com/user-attachments/assets/f19c6bfa-33fe-41b0-b782-c0138b56c0ef)

- Atualizar um livro.
  - A aplicação permite a atualização de um livro, seja do seu titulo, autor, ano de publicação e isbn, passando como parâmetro o seu respectivo ID.
```
PUT -> http://localhost:8080/biblioteca/livros/{id}

{
    "titulo": "Moby Dick",
    "autor": "Guilherme Moreira",
    "anoPublicacao": 2023,
    "isbn": "978-1503280786"
}

```
![image](https://github.com/user-attachments/assets/2971fdbd-b418-4ca1-af26-ea7c2bf444a5)

- Remover um livro.
  A aplicação permite a remoção de um livro, passando como parâmetro o seu respectivo ID.
```
DELETE -> http://localhost:8080/biblioteca/livros/{id}
```
![image](https://github.com/user-attachments/assets/aff4279f-b9b2-403c-b95b-b99e0d34b96a)


