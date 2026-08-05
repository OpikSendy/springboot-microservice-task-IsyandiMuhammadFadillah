# Technical Requirement Document (TRD)

## Project Title: Simple Book Management Microservice
**Candidate**: ISYANDI MUHAMMAD FADILLAH  
**Tech Stack**: Java 17, Spring Boot 3.3.5, PostgreSQL 16, Flyway, Maven, Docker, Swagger OpenAPI 3

---

## 1. System Architecture
The application follows a clean 3-tier Layered Microservice Architecture:

```
[ Client / Postman / Swagger UI ]
               │
               ▼
   [ BookController (REST API) ]
               │
               ▼
    [ BookService / Impl (DTO & Business Logic) ]
               │
               ▼
   [ BookRepository (Spring Data JPA) ]
               │
               ▼
[ PostgreSQL Database (Flyway Migrations) ]
```

---

## 2. Directory Structure
```
src/main/java/com/isyandi/book/
├── BookManagementApplication.java
├── config/
│   └── OpenAPIConfig.java
├── controller/
│   └── BookController.java
├── dto/
│   ├── BookCreateRequest.java
│   ├── BookUpdateRequest.java
│   ├── BookPatchRequest.java
│   └── BookResponse.java
├── entity/
│   └── Book.java
├── exception/
│   ├── BookNotFoundException.java
│   ├── DuplicateIsbnException.java
│   ├── ErrorResponse.java
│   └── GlobalExceptionHandler.java
├── repository/
│   └── BookRepository.java
└── service/
    ├── BookService.java
    └── impl/
        └── BookServiceImpl.java
```
