# E-commerce Backend

Este projeto é o backend do sistema de e-commerce, responsável por gerenciar os dados do carrinho. Ele fornece uma API RESTful que pode ser consumida pelo frontend ou outras aplicações.

## Funcionalidades

- **Gerenciamento do Carrinho**:
  - Adicionar itens ao carrinho.
  - Atualizar a quantidade de itens no carrinho.
  - Remover itens do carrinho.
- **Gerenciamento de Produtos**:
  - Listar produtos disponíveis.
  - Obter informações detalhadas de um produto.
- **Persistência de Dados**:
  - Integração com banco de dados para armazenar produtos, carrinhos e itens do carrinho.

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação principal.
- **Spring Boot**: Framework utilizado para construir a API RESTful.
- **Spring Data JPA**: Para interação com o banco de dados, utilizando o padrão ORM.
- **H2 Database**: Banco de dados em memória para desenvolvimento.
