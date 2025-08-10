# ADR-001: Adopt Modular Monolith Architecture

**Date**: 2025-08-04
**Status**: Accepted

## Context

The `catch-a-flight` project aims to build a flight booking system with multiple bounded contexts (e.g., account
management, flight search, booking). We need an architecture that balances the simplicity of a monolith with the
modularity of microservices to support rapid development, maintainability, and future scalability. A traditional
monolith risks tight coupling, while microservices introduce complexity in deployment and communication.

## Decision

We will adopt a **modular monolith** architecture, where the application is a single deployable unit but internally
organized into loosely coupled modules, each representing a bounded context (e.g., `account`, `flight`, `booking`).
Modules will:

- Be organized as separate packages under `modular-monolith` (e.g., `com.example.account`).
- Communicate via well-defined interfaces and domain events.
- Share a single database but use schema separation (e.g., table prefixes per module) to enforce boundaries.

## Consequences

- **Pros**:
    - Simplifies deployment compared to microservices (single artifact).
    - Enables clear module boundaries, reducing coupling.
    - Supports incremental refactoring toward microservices if needed.
- **Cons**:
    - Risk of module boundaries eroding without strict governance.
    - Shared database may lead to implicit dependencies
