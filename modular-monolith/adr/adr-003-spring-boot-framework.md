# ADR-003: Use Spring Boot as the Application Framework

**Date**: 2025-08-06
**Status**: Accepted

## Context

The `catch-a-flight` project requires a robust framework to handle dependency injection, transaction management, and
infrastructure concerns (e.g., persistence, event publishing). Several frameworks (e.g., Spring Boot, Micronaut,
Quarkus) are available, but we need one that balances developer productivity, community support, and compatibility with
modular monolith principles.

## Decision

We will use **Spring Boot** as the primary framework for the `catch-a-flight` application:

- Leverage Spring’s dependency injection for module components (e.g., `@Service`, `@Component`).
- Use `@Transactional` for managing database transactions.
- Utilize Spring Events or a message broker (e.g., Kafka) for publishing domain events.
- Configure modules as Spring packages under `com.example` (e.g., `com.example.account`).

## Consequences

- **Pros**:
    - Rich ecosystem with extensive libraries and community support.
    - Simplifies configuration of persistence (Spring Data), logging (SLF4J), and event handling.
    - Familiar to most Java developers, reducing learning curve.
- **Cons**:
    - Heavier runtime footprint compared to lightweight frameworks like Quarkus.
    - Risk of tight coupling to Spring if not abstracted properly.
- **Mitigation**:
    - Abstract infrastructure concerns (e.g., repositories, event publishers) behind interfaces.
    - Monitor application performance and optimize as needed.