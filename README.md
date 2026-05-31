# Sistema de Estoque

Sistema de gerenciamento de estoque desenvolvido com Java, Spring Boot e PostgreSQL.

## Integrantes

- João Pedro Lorenzetti
- Yaron Buchler
- Daniel Kawata

## Tecnologias

- Java 21
- Spring Boot 3.3
- PostgreSQL
- Spring Data JPA
- Lombok
- SpringDoc OpenAPI (Swagger)

## Como rodar o projeto

### Pré-requisitos

- Java 21+
- PostgreSQL instalado e rodando
- Maven (ou usar o `mvnw` incluído no projeto)

### Configuração do banco

1. Crie o banco de dados:
```sql
CREATE DATABASE sistema_estoque;
```

2. Atualize o arquivo `src/main/resources/application.properties` com suas credenciais:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/sistema_estoque
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA
```

### Rodando o projeto

```bash
./mvnw spring-boot:run
```

No Windows:
```bash
.\mvnw spring-boot:run
```

### Documentação da API

Com o projeto rodando, acesse:
- Swagger UI: http://localhost:8080/swagger-ui.html
- API Docs: http://localhost:8080/api-docs

## Estrutura do projeto
src/main/java/com/joao/sistema_estoque/
├── controller/       # Endpoints REST
├── service/          # Regras de negócio
├── repository/       # Acesso ao banco de dados
├── model/            # Entidades JPA
│   └── enums/
├── dto/              # Objetos de transferência
│   ├── request/
│   └── response/
└── exception/        # Tratamento de erros

## Entidades

- **Pessoa** (abstrata) → Cliente, Funcionario, DonoEstoque, Fornecedor
- **Produto** → vinculado a Categoria e Fornecedor
- **Inventario** → composto de ItemInventario
- **Compra** → registrada pelo Dono, dá entrada no inventário automaticamente
- **Venda** → registrada pelo Funcionário, baixa o estoque automaticamente

## Endpoints principais

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | /compras | Registra compra e atualiza estoque |
| POST | /vendas | Registra venda e baixa estoque |
| GET | /inventarios/{id}/itens | Visualiza estoque atual |
| POST | /produtos | Cadastra produto |
| POST | /clientes | Cadastra cliente |
| POST | /funcionarios | Cadastra funcionário |