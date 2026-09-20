# PROJECT STATUS

Last Update: 2026-09-07

---

## Current Branch

feature/unit-tests

---

## Current Goal

Complete unit tests for the Service layer.

---

## Current Phase

Phase 4 — Unit Testing

Status: 🟡 In Progress

---

## Completed

### Infrastructure
- [x] Java 17
- [x] Maven
- [x] Spring Boot 4.0.3
- [x] PostgreSQL

### Backend
- [x] REST API
- [x] DTOs
- [x] Validation (@NotBlank, @NotNull, @Min, @Max)
- [x] Pagination and filters (name, releaseYear)
- [x] JWT Authentication (ADMIN, EDITOR, VIEWER)
- [x] Swagger/OpenAPI with Bearer token support
- [x] Global Exception Handler (404, 400)

### Domain
- [x] Game (CRUD completo)
- [x] Genre (CRUD completo)
- [x] Platform (CRUD completo)
- [x] Developer (CRUD completo)
- [x] Publisher (CRUD completo)
- [x] Franchise (CRUD completo)
- [x] GameGenre (relacionamento)
- [x] GamePlatform (relacionamento)

### Tests
- [x] GameServiceTest (6 testes → 0 falhas)
- [ ] DeveloperServiceTest
- [ ] AuthServiceTest

---

## Current Task

Create: DeveloperServiceTest.java
Location: src/test/java/.../service/

---

## Next Tasks

1. Finish DeveloperServiceTest
2. Finish AuthServiceTest
3. Merge feature/unit-tests
4. Docker Compose
5. README profissional
6. LinkedIn posts

---

## Commands

Run: ./mvnw spring-boot:run
Test: ./mvnw test
Build: ./mvnw clean install

---

## Resume Point

Continue from: DeveloperServiceTest.java
