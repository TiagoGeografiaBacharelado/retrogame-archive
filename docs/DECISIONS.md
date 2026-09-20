# Architecture Decisions

This document records important technical decisions.

---

## ADR-001

Decision

Use DTOs instead of exposing entities.

Reason

Separate persistence model from API model.

---

## ADR-002

Decision

Business rules stay in Service layer.

Reason

Controllers should only orchestrate requests.

---

## ADR-003

Decision

Repositories only access the database.

Reason

Avoid business logic in persistence layer.

---

## ADR-004

Decision

Main branch must always remain deployable.

Reason

Keep a stable production-like branch.

---

## ADR-005

Decision

Every new feature uses its own branch.

Example

feature/tests

feature/security

feature/docker

Reason

Safer merges and easier code review.

---

## ADR-006

Decision

README will only be written after implementation stabilizes.

Reason

Avoid rewriting documentation multiple times.

---

## ADR-007

Decision

Documentation will be updated every three major changes.

Reason

Maintain context between development sessions and AI assistants.
