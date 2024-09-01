# Desafio locadora de veículos

![License](https://img.shields.io/github/license/Ednaxx/locadora-de-veiculos-solutis)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.3-green)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen)

### Sobre o projeto

Este repositório contém a resolução do Desafio da Locadora de Veículos,
desenvolvido como parte do processo seletivo para o programa Solutis Dev Trail 2024, pela equipe 9.

### Pré requisitos

- Java JDK 21;
- Apache Maven;
- PostgreSQL;
- Docker (Opcional)

### Instalação do Projeto

1. Faça o clone o repositório:
   ```bash
   git clone https://github.com/Ednaxx/locadora-de-veiculos-solutis.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd locadora-de-veiculos-solutis
   ```
3. Execute no terminal para instalação das dependências do projeto:
   ```bash
   mvn install
   ```
4. Com o Docker aberto, execute no terminal, para rodar localmente o banco de dados Postgres:
   ```bash
   docker-compose up --build
   ```
   ou
   ```bash
   docker run --name postgres-db -p 5432:5432 -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=vehicle-rental -d postgres
      ```
   
5. Configure suas variáveis de ambiente com as credenciais do seu banco de dados
(a assinatura das variáveis está disponível em `/src/main/resources/application.yml`);


6. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

7. Utilize a APi com a seguinte url: http://localhost:8080 
   ou via o Swagger da aplicação: [Swager Locadora de Veículos](http://localhost:8080/swagger-ui/index.html#/)

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
## Desenvolvimento

Este projeto foi desenvolvido em colaboração por uma equipe de 7 pessoas.
Cada membro contribuiu com a implementação e validação das soluções propostas para os exercícios.

### Membros da Equipe

- **Alexandre Morais** - https://github.com/Ednaxx

- **Bruno Ricardo Machado** - https://github.com/brunorm86

- **Gabriel Sena** - https://github.com/Gabriel-SBS

- **José Nathan** - https://github.com/josenathan0

- **Junior Aquino** - https://github.com/GitAquino

- **Larissa Sena** - https://github.com/larissacsena

- **Vinícius Almada** - https://github.com/AlmadaAlmada
