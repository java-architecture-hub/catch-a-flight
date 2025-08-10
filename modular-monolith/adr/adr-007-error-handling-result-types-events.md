# ADR-007: Handle Errors with Result Types and Domain Events

**Date**: 2025-08-10
**Status**: Accepted

## Context

The `account` module handles various error scenarios (e.g., invalid input, password policy violations, unexpected
errors) during account creation. We need a consistent approach to error handling that informs clients of outcomes and
notifies other modules of failures. Throwing exceptions for all cases can make error handling cumbersome, while
unchecked exceptions may lead to inconsistent behavior.

## Decision

We will use **result types** and **domain events** for error handling:

- Define a sealed `CreateAccountResult` type with subclasses (e.g., `Success`, `InputNotValid`, `PasswordPolicyFailure`,
  `InternalFailure`) to represent outcomes.
- Publish domain events (`AccountCreated` for success, `AccountCreationFailed` for failures) to notify other modules.
- Log errors using SLF4J (`@Slf4j`) for debugging and monitoring.
- Throw specific exceptions (e.g., `IllegalArgumentException` for null inputs, `PasswordPolicyException`) only for early
  validation or unrecoverable errors.

## Consequences

- **Pros**:
    - Provides clear, type-safe outcomes for clients.
    - Enables other modules to react to failures via events (e.g., logging, notifications).
    - Improves traceability with structured logging.
- **Cons**:
    - Result types add complexity compared to simple exceptions.
    - Event publishing increases overhead for failure cases.
- **Mitigation**:
    - Standardize result types across modules for consistency.
    - Optimize event publishing for performance (e.g., batching, async publishing).
  