# ADR-004: Implement Event-Driven Communication Between Modules

**Date**: 2025-08-07
**Status**: Accepted

## Context

Modules in the `catch-a-flight` modular monolith need to communicate without direct dependencies to maintain loose
coupling. For example, the `account` module must notify other modules (e.g., `notification`, `auth`) when an account is
created. Direct method calls between modules would violate encapsulation, while synchronous REST calls add complexity.

## Decision

We will use **event-driven communication** for inter-module interactions:

- Each module will publish domain events (e.g., `AccountCreated`, `AccountCreationFailed`) using an `EventPublisher`
  interface.
- Events will be defined as immutable objects with relevant data (e.g., `accountId`, `email`).
- Initially, use Spring’s `ApplicationEventPublisher` for in-memory event handling within the monolith.
- Plan for future integration with a message broker (e.g., Kafka, RabbitMQ) if modules are extracted to microservices.

## Consequences

- **Pros**:
    - Decouples modules, allowing independent development and testing.
    - Enables asynchronous processing, improving responsiveness.
    - Simplifies integration with external systems via events.
- **Cons**:
    - Eventual consistency may introduce complexity in handling failures.
    - In-memory events may not scale for high loads.
- **Mitigation**:
    - Implement retry mechanisms for event delivery.
    - Design idempotent event handlers to handle duplicate events.
    - Evaluate message broker adoption as load increases.
  