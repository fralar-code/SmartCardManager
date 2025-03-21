## 🔧 Technologies  
- Java 17  
- Spring Boot  
- Spring Security  
- Thymeleaf
- Hibernate & JPA  
- MySQL  
- Maven

## ✨ Features  
The website emulates the management of card transactions and payments. An ADMIN user—who can be thought of as a shopkeeper selling rechargeable gift cards—can create a card with the amount of money provided by the user in the store. This allows the user to check their current credit at any time. All transactions are logged in the database with useful information.
- 📌 Users can check card balance  
- 🛒 Merchants can process charges & top-ups  
- 🛡️ Admins can create, block, and manage cards  
- 📊 Reports for transactions and merchants

## 🚀 Setup & Installation  
1. Clone this repository ```git clone https://github.com/fralar-code/SmartCardManager.git```
2. Configure application.properties
4. Run the project ```mvn spring-boot:run```

## 🏗️ Project Architecture  

In a **Spring Boot** application, it is common to adopt a multi-layered architecture that separates responsibilities into distinct components:  

1. **Entity**: Represents domain entities and maps them to database tables. Using **JPA (Java Persistence API)** annotations, you define classes corresponding to database tables, facilitating CRUD operations.  

2. **Repository**: Interfaces extending `JpaRepository` providing methods to interact with the database. This layer handles data access, allowing operations such as saving, updating, deleting, and retrieving entities.  

3. **Service**: Contains the application's business logic. Service classes orchestrate operations between the controller and the repository, applying necessary business rules.  

4. **Controller**: Manages HTTP requests and returns appropriate responses. Controllers receive user requests, call the appropriate services, and return responses.  

5. **DTO (Data Transfer Object)**: Classes used to transfer data between different application layers, especially between the client and the server. DTOs help encapsulate data and prevent direct exposure of domain entities, enhancing security and maintainability.  

This layered separation improves **maintainability, testability, and scalability**, following the **separation of concerns** principle.  
