# Catch a Flight - Modular Monolith

![Java](https://img.shields.io/badge/Java-24-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green) ![License](https://img.shields.io/badge/License-MIT-yellow)

The **Catch a Flight - Modular Monolith** is a showcase of best-in-class software engineering practices within the Java and Spring Boot ecosystem, leveraging **JDK 24** and **Spring Boot 3**. This variant emphasizes a **Modular Monolith** architecture, prioritizing simplicity, cohesion, and maintainability while demonstrating modern design patterns and technologies.

This project is part of the broader **Catch a Flight** initiative, which explores two architectural variants: **Modular Monolith** (this repository) and **Microservices**. The modular monolith variant serves as a robust, single-deployable application with clear module boundaries, making it ideal for teams seeking simplicity without sacrificing scalability or flexibility.

## Key Features

- **Domain-Driven Design (DDD)** and **Event Storming** for precise domain modeling and clear business logic.
- **Hexagonal Architecture** to ensure modularity, loose coupling, and testability.
- **Event-Driven Design** for responsive, decoupled interactions within the monolith.
- **Data-Oriented Programming** to optimize data handling and processing.
- **Comprehensive API Support**:
    - REST for standard HTTP-based communication.
    - GraphQL for flexible, client-driven queries.
    - gRPC for high-performance, type-safe RPCs.
    - Server-Side Events (SSE) for real-time updates.
    - WebSockets for bidirectional communication.
- **Persistence Layer**:
    - **SQL Databases**: H2 (in-memory for development), PostgreSQL, MySQL.
    - **NoSQL Databases**: Redis, MongoDB, Elasticsearch, Cassandra, InfluxDB, Neo4j.
- **Full Observability**: Advanced logging, monitoring, and tracing for complete system visibility.
- **Testing**: Comprehensive unit, integration, and end-to-end tests to ensure reliability.

## Goals

The **Modular Monolith** variant aims to:
- Demonstrate how to structure a cohesive, single-deployable application with clear module boundaries.
- Showcase best practices for **JDK 24** and **Spring Boot 3** in a real-world context.
- Provide a foundation for evolving into a microservices architecture if needed.
- Explore modern architectural patterns and technologies within a simplified deployment model.

## Getting Started

### Prerequisites

- **JDK 24**: Ensure Java 24 is installed. [Download JDK 24](https://jdk.java.net/24/).
- **Maven**: For dependency management and building the project.
- **Docker**: For running databases and other dependencies locally (optional).
- **IDE**: IntelliJ IDEA, Eclipse, or any IDE with Spring Boot support.
- **Database Access**:
    - H2 (embedded, no setup required for development).
    - PostgreSQL/MySQL for production-like environments.
    - Redis, MongoDB, Elasticsearch, Cassandra, InfluxDB, or Neo4j for NoSQL use cases (optional).
