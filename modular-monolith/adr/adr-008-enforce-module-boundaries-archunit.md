# ADR-008: Enforce Module Boundaries with Architecture Tests

**Date**: 2025-08-10
**Status**: Accepted

## Context

In a modular monolith, maintaining strict boundaries between modules is critical to prevent tight coupling. Without
enforcement, developers may accidentally introduce dependencies (e.g., `account` module calling `booking` module
directly), undermining modularity. Manual code reviews are insufficient to catch all violations.

## Decision

We will use **ArchUnit** to enforce module boundaries through automated architecture tests:

- Define rules to prevent direct dependencies between modules (e.g., `com.example.account` should not access
  `com.example.booking`).
- Ensure modules only interact via interfaces (e.g., `CreateAccountUseCase`) or events.
- Restrict access to module-specific database tables (e.g., `account_users`) to the owning module’s repositories.
- Run architecture tests as part of the CI/CD pipeline.

## Consequences

- **Pros**:
    - Automates enforcement of modular monolith principles.
    - Catches boundary violations early in development.
    - Encourages discipline in maintaining loose coupling.
- **Cons**:
    - Adds overhead to write and maintain architecture tests.
    - May require developer training on ArchUnit.
- **Mitigation**:
    - Provide templates for common ArchUnit rules.
    - Integrate tests into Maven/Gradle builds for seamless execution.
  