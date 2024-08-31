# Desafio locadora de veículos

## Sobre o projeto

Este repositório contém a resolução do Desafio da Locadora de Veículos,
desenvolvido como parte do processo seletivo para o programa Solutis Dev Trail 2024.

## Pré requisitos

- Java JDK 21;
- Apache Maven;
- PostgreSQL;

## Como executar o projeto

- Configure o banco de dados local. Pode-se usar um container no docker: 
```docker run --name postgres-db -p 5432:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=vehicle-rental -d postgres```;
- Na pasta do código fonte, instale as dependências do Maven: ```mvn install```;
- Configure suas variáveis de ambiente com as credenciais do seu banco de dados
(a assinatura das variáveis está disponível em /src/main/resources/application.yml);
- Execute a aplicação: ```mvn spring-boot:run```;
- A API ficará disponível em http://localhost:8080.

## Diagrama de entidades

```mermaid
classDiagram
    %% Enumerations
    class Sexo {
        <<enumeration>>
        +MASCULINO
        +FEMININO
    }

    class Categoria {
        <<enumeration>>
        +HATCH_COMPACTO
        +HATCH_MEDIO
        +SEDAN_COMPACTO
        +SEDAN_MEDIO
        +SEDAN_GRANDE
        +MINIVAN
        +ESPORTIVO
        +UTILITARIO_COMERCIAL
    }

    %% Classes
    class Pessoa {
        +id: Long
        +nome: String
        +dataNascimento: String
        +cpf: String
        +email: String
    }

    class Motorista {
        +numeroCNH: String
    }

    class Funcionario {
        +matricula: String
    }

    class ApoliceSeguro {
        +id: Long
        +valorFranquia: BigDecimal
        +protecaoTerceiros: Boolean
        +protecaoCausasNaturais: Boolean
        +protecaoRoubo: Boolean
    }

    class Aluguel {
        +id: Long
        +dataPedido: LocalDate
        +dataDevolucao: LocalDate
        +valorTotal: BigDecimal
    }

    class Carro {
        +id: Long
        +chassi: String
        +cor: String
        +valorDiaria: String
        +datasOcupadas: List<LocalDate>
    }

    class ModeloCarro {
        +id: Long
        +descricao: String
    }

    class Fabricante {
        +id: Long
        +nome: String
    }

    class Acessorio {
        +id: Long
        +descricao: String
    }

    class CarrinhoCompra {
        +id: Long
    }

    %% Relationships
    Pessoa <|-- Motorista : é uma
    Pessoa <|-- Funcionario : é uma
    Motorista "1" -- "0..*" Aluguel : alugueis
    Aluguel "1" -- "1" ApoliceSeguro : apolice
    Aluguel "1" -- "0..*" Carro : carro
    Motorista "1" -- "1" CarrinhoCompra : carrinhoCompra
    CarrinhoCompra "1" -- "0..*" Carro : listaCarros
    Carro "1" -- "0..*" Acessorio : acessorios
    Carro "1" -- "1" ModeloCarro : modelo
    ModeloCarro "1" -- "0..*" Fabricante : fabricante
    ModeloCarro "1" -- "1" Categoria : categoria
```
