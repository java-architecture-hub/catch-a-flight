# ADR-005: Share a Single Database with Schema Separation

**Date**: 2025-08-08
**Status**: Accepted

## Context

The modular monolith requires a persistence strategy that supports module independence while leveraging the simplicity
of a single deployable unit. Using a single database simplifies deployment and transactions but risks implicit coupling
between modules. Separate databases per module increase operational complexity.

## Decision

We will use a **single database** with schema separation for each module:

- Each module (e.g., `account`) will use tables prefixed with the module name (e.g., `account_users`).
- Use Spring Data JPA for repository implementations (e.g., `CreateAccountRepository`).
- Enforce module boundaries by restricting access to a module’s tables via repository interfaces.
- Use `@Transactional` to manage transactions within a module’s scope.

## Consequences

- **Pros**:
    - Simplifies database management and transactions within the monolith.
    - Reduces operational overhead compared to multiple databases.
    - Leverages Spring Data JPA for rapid development.
- **Cons**:
    - Risk of modules accessing each other’s tables if not properly restricted.
    - Shared database may become a bottleneck at scale.
- **Mitigation**:
    - Use architecture tests (e.g., ArchUnit) to prevent cross-module table access.
    - Monitor database performance and plan for sharding or extraction if needed.
  