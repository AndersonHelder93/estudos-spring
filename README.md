# Usuarios API

API REST simples desenvolvida com Spring Boot para estudos.

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
