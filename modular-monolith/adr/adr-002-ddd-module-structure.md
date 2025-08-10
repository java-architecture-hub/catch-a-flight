# ADR-002: Use Domain-Driven Design (DDD) for Module Structure

**Date**: 2024-08-05
**Status**: Accepted

## Context

The `catch-a-flight` application involves multiple domains (e.g., account management, flight search), each with distinct business rules. To ensure maintainability and alignment with business requirements, we need a structured approach to model these domains. A DDD approach can help define clear bounded contexts and domain models, but it requires discipline to implement effectively within a monolith.

## Decision

We will apply **Domain-Driven Design (DDD)** principles to structure each module:

- Each module (e.g., `account`) represents a bounded context.
- Domain entities (e.g., `Account`) and value objects will encapsulate core business logic.
- Use cases (e.g., `CreateAccountUseCase`) will define module entry points.
- Domain services (e.g., `CreateAccountService`) will orchestrate business logic.
- Domain events (e.g., `AccountCreated`, `AccountCreationFailed`) will facilitate communication between modules.

## Consequences

- **Pros**:
  - Aligns code structure with business domains, improving clarity.
  - Encourages encapsulation and separation of concerns.
  - Facilitates event-driven integration between modules.
- **Cons**:
  - Increased complexity due to DDD terminology and patterns.
  - Requires team training to ensure consistent application.
- **Mitigation**:
  - Provide DDD training and guidelines for developers.
  - Use annotations (e.g., `@DomainService`) to clearly mark DDD components.
