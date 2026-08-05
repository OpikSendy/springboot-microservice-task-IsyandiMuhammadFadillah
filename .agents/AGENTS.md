# Agent Guidelines for Book Management Microservice

## Candidate Context
- Candidate: ISYANDI MUHAMMAD FADILLAH
- Tech Stack: Java 17, Spring Boot 3.3.5, PostgreSQL, Flyway, Maven, JUnit 5, Mockito.

## Code Standards
- Use DTOs for request and response mapping. Never expose Entities directly in Controller endpoints.
- Enforce `@Valid` bean validations on all incoming request bodies.
- Handle all exceptions using `@RestControllerAdvice` in `GlobalExceptionHandler.java`.
