# CAMS

Customer Accounts Management System, a Java console application for viewing account reports.

## Requirements

- Java 25
- Maven

## Build and Run

```bash
mvn clean package
java -jar target/pamsapp.jar
```

## Class Diagram

![Detailed Design Diagram](./diagram/Class_Diagram.png)

## Detailed Design Diagram


```mermaid
classDiagram

    class CAMSApplication {
        +main(args: String[]) void
    }

    class AccountService {
        -accountRepository: AccountRepository
        +getAllAccountsSortedByBalanceDesc() List~Account~
        +getPlatinumAccountsSortedByBalanceDesc() List~Account~
        +calculateLiquidityPosition() BigDecimal
    }

    class JsonReportService {
        -accountService: AccountService
        +getAllAccountsJsonReport() String
        +getPlatinumAccountsJsonReport() String
        -accountToJson(account: Account) String
    }

    class AccountRepository {
        -accounts: List~Account~
        +findAll() List~Account~
    }

    class CustomerRepository {
        -customers: List~Customer~
        +findAll() List~Customer~
        +findById(customerId: int) Customer
    }

    class Customer {
        -customerId: int
        -firstName: String
        -lastName: String
        +getCustomerId() int
        +getFirstName() String
        +getLastName() String
    }

    class Account {
        -accountId: long
        -accountNumber: String
        -accountType: String
        -dateOpened: LocalDate
        -balance: BigDecimal
        -customer: Customer
        +getTier() AccountTier
        +getBalance() BigDecimal
        +getCustomer() Customer
    }

    class AccountTier {
        <<enumeration>>
        STANDARD
        SILVER
        GOLD
        PLATINUM
    }

    CAMSApplication --> AccountService
    CAMSApplication --> JsonReportService

    JsonReportService --> AccountService

    AccountService --> AccountRepository
    AccountRepository --> Account

    CustomerRepository --> Customer
    Account --> Customer
    Account --> AccountTier

    Customer "1" --> "1..*" Account : owns
```

## GitHub Repository

[https://github.com/Darkhaamn/miu-cs425-quiz1](https://github.com/Darkhaamn/miu-cs425-quiz1)

## Developed By

Darkhanbayar