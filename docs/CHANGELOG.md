# Changelog

---

## [Unreleased]

### Added
- Unit tests for GameService (6 tests)
- Unit tests for DeveloperService (in progress)

---

## [0.4.0] — 2026-06-03

### Added
- Swagger/OpenAPI documentation
- Bearer token support in Swagger UI

---

## [0.3.0] — 2026-05-30

### Added
- JWT Authentication
- Roles: ADMIN, EDITOR, VIEWER
- User entity and repository
- AuthService, AuthController
- JwtService, JwtFilter
- SecurityConfig, UserDetailsServiceImpl

---

## [0.2.0] — 2026-05-25

### Added
- Pagination and filters for GET /games
- Genre and Platform CRUD
- GameGenre and GamePlatform relationships
- Global exception handler
- DTO validations

---

## [0.1.0] — 2026-05-01

### Added
- Spring Boot project setup
- PostgreSQL configuration
- Game CRUD complete
- Developer, Publisher, Franchise CRUD
- DTO layer
- ResourceNotFoundException
