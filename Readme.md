# Usuarios API

API REST simples desenvolvida com Spring Boot para estudos.

## Tecnologias
- Java 17
- Spring Boot
- Maven

## Funcionalidades
- Cadastro de usuários
- Validação de dados
- Tratamento global de erros
- Respostas HTTP padronizadas

## Endpoints

### GET /usuarios
Lista usuários cadastrados.

### POST /usuarios
Cria um novo usuário.

Exemplo de body:
```json
{
  "nome": "João",
  "idade": 25
}

