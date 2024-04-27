
#  Banking system with remittance,acceptance of deposit 

Introduction

This project provides a simple RESTful API for managing bank accounts. It allows you to create accounts, retrieve account information, and perform deposit,withdraw and remmitance operation




## Features
1) Account Creation: The API allows for the creation of new accounts. Users can specify the account ID, account name, account type, and initial balance.

2) Account Retrieval: Users can retrieve the details of an existing account by providing the account ID.

3) Deposit: The API supports deposit operations. Users can deposit a specified amount into an existing account.

4) Remittance: The API supports remittance operations. Users can transfer a specified amount from one account to another.

5) Transaction History: Users can retrieve the transaction history of an account.

6) ccount Deletion: Users can delete an existing account by making a DELETE request.



## Tech Stack

**Server:** Java,Spring Boot,Spring Data JPA,H2 Database,MySQL

**Tool:** Postman

## Prerequisites
1) Java Development Kit (JDK) installed on your system.

2) Maven installed on your system.

3) Git installed on your system.





## Installation

```bash
 git clone https://github.com/rushabhkhandhar/JPMC-Online_banking_system
 mvn clean install
 mvn spring-boot:run
 http://localhost:8080
```


## API Reference

#### Create Account

```http
POST /api/account
```

#### Get Account Details

```http
POST /api/account/{accountId}
```

#### Deposit to Account

```http
PUT /api/account/{accountId}/deposit?amount={amount}

```
Similarly, you can test the other APIs using Postman


## Feedback

If you have any feedback, please reach out to me at rushabhkhandhar38@gmail.com

