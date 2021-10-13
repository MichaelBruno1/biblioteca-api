<<<<<<< Updated upstream
# biblioteca-api
=======
# Biblioteca API

## Sobre
Este projeto consiste em uma API REST responsável por gerenciar uma biblioteca, permitindo o cadastro e remoção de livros e usuarios, permitindo que um usuario alugue ou devolva um livro.

## Requisitos
 - Maven
 - Java 11
 - PostgreSQL

## Configuração do projeto
Na pasta _src\main\resources_ abra o arquivo _application.properties_ adicione as configurações do banco de dados:
> spring.datasource.url={CONNECTION_STRING}
> 
> spring.datasource.username={USER}
> 
> spring.datasource.password={PASSWORD}
    
## Rodando do projeto

Para rodar o projeto, abra o terminal na raiz do projeto e execute o comando:
```shell script
mvn spring-boot:run
```
## Testando a API
Para testar a API basta importar a coleção de chamada do Postman que se encontra na raiz do projeto _biblioteca.postman_collection.json_ .

Ou acessar o endereço http://localhost:8080/swagger-ui.html para execução do Swagger.

> **_NOTE:_**  Apena usuários cadastrados podem alugar um livro.

>>>>>>> Stashed changes
