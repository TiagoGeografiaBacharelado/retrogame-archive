# RetroGame Archive API - Architecture

## Purpose

This document describes the architecture adopted by the project and the responsibility of each package.

---

# Project Structure

```
src
├── main
│   ├── java
│   │   └── br.com.tiagodev.retrogamearchive
│   │
│   ├── config
│   ├── controller
│   ├── domain
│   │   ├── dto
│   │   └── model
│   ├── exception
│   ├── repository
│   ├── security
│   ├── service
│   └── RetroGameArchiveApplication
│
│
└── test
    └── br.com.tiagodev.retrogamearchive
        └── service
```

---

# Layer Responsibilities

## Controller

Receives HTTP requests.

Responsibilities:

- Receive requests
- Validate input
- Return HTTP responses

Controllers must not contain business rules.

---

## Service

Contains business logic.

Responsibilities:

- Business rules
- Validation
- Communication between Controller and Repository

---

## Repository

Responsible only for database access.

Repositories must not contain business rules.

---

## Domain

Represents the application domain.

### model

Contains JPA entities.

Example:

- Game
- Platform
- Genre
- Developer
- Publisher
- Franchise

---

### dto

Contains Data Transfer Objects.

DTOs prevent exposing entities directly.

---

## Exception

Contains custom exceptions and global exception handlers.

---

## Security

Authentication and authorization.

Current technologies:

- Spring Security
- JWT

---

## Config

Application configuration.

Examples:

- Swagger
- Beans
- Security configuration
