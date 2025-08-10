# ADR-006: Implement Input Validation in Domain Services

**Date**: 2025-08-09
**Status**: Accepted

## Context

The `account` module, and likely other modules, requires robust input validation to ensure commands (e.g.,
`CreateAccountCommand`) meet business rules before processing. Validation can be handled in controllers, services, or
dedicated validators. Centralizing validation in controllers risks bloating them, while embedding it in entities may
violate separation of concerns.

## Decision

We will implement input validation in **dedicated validator classes** within domain services:

- Each module will have validator classes (e.g., `CreateAccountCommandValidator`) as nested or standalone components.
- Validators will return a result type (e.g., `InputValidationResult` with `Valid` or `NotValid` subclasses).
- Domain services (e.g., `CreateAccountService`) will orchestrate validation before proceeding with business logic.
- Use Spring’s `@Component` for validators to enable dependency injection.

## Consequences

- **Pros**:
    - Centralizes validation logic, improving maintainability.
    - Keeps domain services focused on orchestration rather than validation details.
    - Enables reuse of validators across use cases if needed.
- **Cons**:
    - Adds a layer of complexity with additional classes.
    - Risk of inconsistent validation if not standardized across modules.
- **Mitigation**:
    - Define a common validation framework or interface for all modules.
    - Use unit tests to ensure comprehensive validation coverage.
