# 🕹️ RetroGame Archive API

> REST API for managing a retro game catalog — built with Java 17 and Spring Boot 4

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.3-brightgreen?style=flat-square&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=flat-square&logo=postgresql)
![JWT](https://img.shields.io/badge/JWT-Auth-black?style=flat-square&logo=jsonwebtokens)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203.1-85EA2D?style=flat-square&logo=swagger)

---

## 📌 About

RetroGame Archive is a REST API for cataloging retro games, developers, publishers, platforms and genres.

Built as a portfolio project to demonstrate real-world backend development skills including layered architecture, JWT security, data validation, pagination, and unit testing.

---

## 🚀 Features

- ✅ Full CRUD for Games, Developers, Publishers, Franchises, Genres and Platforms
- ✅ Many-to-many relationships via junction entities (GameGenre, GamePlatform)
- ✅ JWT Authentication with role-based access control
- ✅ Three access levels: `ADMIN`, `EDITOR`, `VIEWER`
- ✅ Pagination and filters (`name`, `releaseYear`)
- ✅ DTO layer with input validation
- ✅ Global exception handling (404, 400)
- ✅ Interactive API documentation via Swagger UI
- ✅ Unit tests with JUnit 5 and Mockito

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 4.0.3 |
| Database | PostgreSQL 16 |
| ORM | Spring Data JPA / Hibernate |
| Security | Spring Security + JWT (jjwt 0.12.6) |
| Documentation | SpringDoc OpenAPI 3.0.1 |
| Tests | JUnit 5 + Mockito |
| Build | Maven |

---

## 🏗️ Architecture

```
src/main/java/br/com/tiagodev/retrogamearchive/
│
├── config/          # Security, Swagger, JWT Filter
├── controller/      # REST endpoints (thin layer)
├── service/         # Business logic
├── repository/      # Database access (Spring Data JPA)
├── domain/
│   ├── model/       # JPA Entities
│   └── dto/         # Data Transfer Objects
└── exception/       # Global exception handling
```

**Request flow:**
```
HTTP Request → Controller → Service → Repository → Database
                                                      ↓
HTTP Response ← Controller ← Service ← Repository ←──┘
```

---

## 🗄️ Data Model

| Entity | Description |
|---|---|
| `Game` | Main entity — name, year, description, players |
| `Developer` | Game development studio |
| `Publisher` | Game publishing company |
| `Franchise` | Game series (e.g. Sonic, Mario) |
| `Genre` | Game genre (e.g. Platform, RPG) |
| `Platform` | Game platform (e.g. Sega Mega Drive) |
| `GameGenre` | Junction table — Game ↔ Genre |
| `GamePlatform` | Junction table — Game ↔ Platform |
| `User` | API user with role (ADMIN, EDITOR, VIEWER) |

---

## 🔐 Access Control

| Method | Endpoint | ADMIN | EDITOR | VIEWER |
|---|---|---|---|---|
| POST | `/auth/register` | ✅ | ✅ | ✅ |
| POST | `/auth/login` | ✅ | ✅ | ✅ |
| GET | `/games` | ✅ | ✅ | ✅ |
| GET | `/games/{id}` | ✅ | ✅ | ✅ |
| POST | `/games` | ✅ | ✅ | ❌ |
| PUT | `/games/{id}` | ✅ | ✅ | ❌ |
| DELETE | `/games/{id}` | ✅ | ❌ | ❌ |

---

## ⚡ Getting Started

### Prerequisites

- Java 17+
- PostgreSQL 16+
- Maven (or use the included `./mvnw`)

### 1. Clone the repository

```bash
git clone https://github.com/tiagodev/retrogame-archive.git
cd retrogame-archive
```

### 2. Create the database

```bash
psql -U postgres -c "CREATE DATABASE retrogame_archive;"
```

### 3. Configure application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/retrogame_archive
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=your-secret-key-at-least-32-characters
jwt.expiration=86400000
```

### 4. Run the application

```bash
./mvnw spring-boot:run
```

### 5. Access Swagger UI

```
http://localhost:8080/swagger-ui.html
```

---

## 🧪 Running Tests

```bash
./mvnw test
```

---

## 📖 API Usage Example

### Register a user

```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Tiago",
    "email": "tiago@email.com",
    "password": "123456",
    "role": "ADMIN"
  }'
```

### Login

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "tiago@email.com",
    "password": "123456"
  }'
```

### Create a game (authenticated)

```bash
curl -X POST http://localhost:8080/games \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN_HERE" \
  -d '{
    "name": "Sonic the Hedgehog",
    "releaseYear": 1991,
    "description": "Classic Sega platformer",
    "numberOfPlayers": 1,
    "developerId": 1
  }'
```

### List games with pagination and filter

```bash
curl "http://localhost:8080/games?name=sonic&page=0&size=10" \
  -H "Authorization: Bearer YOUR_TOKEN_HERE"
```

---

## 📁 Project Docs

| File | Description |
|---|---|
| `docs/ARCHITECTURE.md` | Architecture decisions |
| `docs/CHANGELOG.md` | Version history |
| `docs/ROADMAP.md` | Project roadmap |
| `docs/PROJECT_STATUS.md` | Current development status |

---

## 👨‍💻 Author

**Tiago**
Junior Backend Developer — Brazil

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue?style=flat-square&logo=linkedin)](https://www.linkedin.com/in/tiago-carneiro-nascimento/)
[![GitHub](https://img.shields.io/badge/GitHub-Follow-black?style=flat-square&logo=github)](https://github.com/TiagoGeografiaBacharelado/retrogame-archive.git)

---

## 📄 License

This project is for portfolio and learning purposes.
