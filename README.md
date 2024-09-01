# Desafio locadora de veículos

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

   class Car {
      +UUID id
      +String licensePlate
      +String chassis
      +String color
      +BigDecimal dailyRate
      +String imageURL
      +List~LocalDate~ occupiedDates
      +boolean isAvailableToRent(LocalDate, LocalDate)
      +void blockDates(LocalDate, LocalDate)
   }

   class CarType {
      +UUID id
      +String description
      +Category category
   }

   class Accessory {
      +UUID id
      +String name
      +String description
   }

   class Rental {
      +UUID id
      +LocalDate orderDate
      +LocalDate returnDate
      +BigDecimal totalValue
   }

   class InsurancePolicy {
      +UUID id
      +BigDecimal franchiseValue
      +boolean thirdPartyProtection
      +boolean naturalCausesProtection
      +boolean theftProtection
   }

   class Manufacturer {
      +UUID id
      +String name
   }

   class Person {
      +UUID id
      +String name
      +LocalDate birthDate
      +String CPF
      +Gender gender
      +String email
   }

   class Driver {
      +String CNH
   }

   class ShoppingCart {
      +UUID id
   }

   Car --> CarType : 1..* - 1
   Car --> Rental : 1 - 0..*
   Car --> Accessory : 0..* - 0..*
   CarType --> Manufacturer : 1..* - 1
   Rental --> InsurancePolicy : 1 - 1
   Rental --> Car : 0..* - 1
   Rental --> Driver : 1 - 1
   Driver --|> Person
   Driver --> ShoppingCart : 1 - 1
   ShoppingCart --> Car : 0..* - 0..*
   CarType -- Category : 1 - 1
   Person -- Gender : 1 - 1

   class Category {
      <<enumeration>>
      COMPACT_HATCH
      MEDIUM_HATCH
      COMPACT_SEDAN
      MEDIUM_SEDAN
      LARGE_SEDAN
      MINIVAN
      SPORTS_CAR
      COMMERCIAL_UTILITY
   }

   class Gender {
      <<enumeration>>
      MASCULINO
      FEMININO
   }
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
