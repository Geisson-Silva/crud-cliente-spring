# 📌 API de Clientes – Spring Boot (CRUD)

Este projeto é uma **API REST desenvolvida com Spring Boot**, que implementa um **CRUD completo de clientes**, seguindo boas práticas de organização em camadas (Controller, Service, Repository e DTOs).

O objetivo principal deste projeto é **portfólio**, demonstrando domínio dos fundamentos de desenvolvimento backend com Java e Spring Boot.

---

## 🛠️ Tecnologias Utilizadas

- Java 17  
- Spring Boot  
- Spring Web  
- Spring Data JPA  
- Hibernate  
- Maven  
- Banco de dados H2 (em memória)  
- Swagger / OpenAPI  

---

## 📂 Estrutura do Projeto

```text
src/main/java
 └── com.example
     ├── controller
     ├── service
     ├── repository
     ├── domain
     └── dto
Descrição das camadas
Controller: responsável por expor os endpoints REST.

Service: contém a lógica de negócio da aplicação.

Repository: acesso aos dados utilizando Spring Data JPA.

Domain: entidades mapeadas para o banco de dados.

DTO: objetos de transferência de dados para requests e responses.

📌 Funcionalidades
Criar cliente

Listar clientes

Listar clientes com paginação, ordenação e filtros

Buscar cliente por ID

Atualizar cliente

Remover cliente

Validação de e-mail duplicado

🔍 Endpoints Principais
➕ Criar cliente
POST /clientes
📄 Listar clientes (com paginação e filtros)
GET /clientes
Parâmetros opcionais
nome

email

page

size

sort

Exemplo
GET /clientes?nome=joao&page=0&size=10&sort=id,asc
🔎 Buscar cliente por ID
GET /clientes/{id}
✏️ Atualizar cliente
PUT /clientes/{id}
❌ Deletar cliente
DELETE /clientes/{id}
📄 Paginação e Ordenação
A listagem de clientes utiliza os recursos nativos do Spring Data:

Pageable

Page<T>

@PageableDefault

Configuração padrão:

Página inicial: 0

Tamanho da página: 10

Ordenação: id crescente

📑 Documentação da API (Swagger)
A API possui documentação automática via Swagger.

Após iniciar a aplicação, acesse:

http://localhost:8080/swagger-ui.html
ou

http://localhost:8080/swagger-ui/index.html
▶️ Como Executar o Projeto
Pré-requisitos
Java 17 ou superior

Maven

Passos
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
mvn spring-boot:run
A aplicação estará disponível em:

http://localhost:8080
🧠 Conceitos Aplicados
API RESTful

Boas práticas com Spring Boot

Separação de responsabilidades

Uso de DTOs para isolamento da camada de domínio

Paginação, ordenação e filtros com Spring Data JPA

Organização em camadas

🎯 Objetivo do Projeto
Este projeto foi desenvolvido com foco em aprendizado e portfólio, servindo como base para aplicações maiores e demonstrando conhecimento prático em desenvolvimento backend com Java e Spring Boot.