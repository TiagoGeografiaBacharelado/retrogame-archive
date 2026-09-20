# Development Guide

## Branch Strategy

main
Stable production branch.

feature/*
Development branches.

Example:

feature/game-tests

feature/security

feature/docker

---

## Development Flow

1. Create feature branch
2. Implement feature
3. Test locally
4. Commit
5. Push
6. Open Pull Request
7. Merge into main

---

## Commit Pattern

feat:
New feature

fix:
Bug fix

refactor:
Internal improvements

test:
Tests

docs:
Documentation

chore:
Maintenance

Examples

feat(game): create CRUD

test(service): add GameService unit tests

docs: add architecture documentation

fix(game): validate release year

## Next Session

Current branch:
feature/tests

Current objective:
Implement GameService unit tests using JUnit 5 + Mockito.

Next file:
GameServiceTest.java

Stop point:
Mockito configuration completed.

Do not change:
- Project architecture
- Package structure
- DTO pattern
- JWT implementation

Next commit:
test(game): add unit tests for GameService
