# Product Requirement Document (PRD)

## Project Title: Simple Book Management Microservice
**Candidate**: ISYANDI MUHAMMAD FADILLAH  
**Role**: Junior Java Developer  
**Date**: August 5, 2026  

---

## 1. Executive Summary
The Simple Book Management Microservice provides a RESTful backend service to perform complete CRUD (Create, Read, Update, Partial Update, Delete) operations on book inventory records. The microservice is built using Spring Boot 3.3.5 with Java 17, Spring Data JPA, PostgreSQL, Flyway DB Migrations, and Swagger OpenAPI 3 documentation.

---

## 2. Key Features & Business Requirements
1. **Add New Book (`POST /api/books`)**: Registers a new book into the repository with title, author, unique ISBN, and published date.
2. **Retrieve All Books (`GET /api/books`)**: Returns a complete list of stored books.
3. **Retrieve Book By ID (`GET /api/books/{id}`)**: Returns details of a specific book by its ID.
4. **Full Update Book (`PUT /api/books/{id}`)**: Overwrites all fields of an existing book record.
5. **Partial Update Book (`PATCH /api/books/{id}`)**: Updates specified attributes (e.g. title only) without altering unmentioned fields.
6. **Delete Book (`DELETE /api/books/{id}`)**: Removes a book record permanently.

---

## 3. Non-Functional Requirements
- **Reliability & Validation**: Validates all incoming payloads using Jakarta Bean Validation (`@NotBlank`, `@PastOrPresent`).
- **Data Integrity**: Enforces unique constraints on ISBN numbers across all create and update operations.
- **Graceful Error Handling**: Implements a global `@RestControllerAdvice` returning standard `ErrorResponse` objects with HTTP status codes (400 Bad Request, 404 Not Found, 409 Conflict).
- **Maintainability**: Clear separation of concerns into Controller, Service, DTO, Repository, and Entity layers.
