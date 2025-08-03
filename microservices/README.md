# Catch a Flight - Microservices

![Java](https://img.shields.io/badge/Java-24-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green) ![Kubernetes](https://img.shields.io/badge/Kubernetes-1.28-blue) ![License](https://img.shields.io/badge/License-MIT-yellow)

The **Catch A Flight - Microservices** is a comprehensive demonstration of modern software engineering practices within the Java and Spring Boot ecosystem, leveraging **JDK 24** and **Spring Boot 3**. This variant emphasizes a **Microservices** architecture, focusing on distributed, scalable, and cloud-native systems with robust observability and deployment strategies.

This project is part of the broader **Catch A Flight** initiative, which explores two architectural variants: **Modular Monolith** and **Microservices** (this repository). The microservices variant showcases a distributed system with independent services, orchestrated via Kubernetes, and integrated with advanced messaging and API management for high scalability and resilience.

## Key Features

- **Domain-Driven Design (DDD)** and **Event Storming** for precise domain modeling across services.
- **Hexagonal Architecture** to ensure modularity and loose coupling within each microservice.
- **Event-Driven Design** with **Async Messaging** (RabbitMQ) and **Event Streaming** (Apache Kafka) for decoupled, responsive systems.
- **Cloud Design Patterns** to support scalability, fault tolerance, and distributed architectures.
- **Comprehensive API Support**:
    - REST for standard HTTP-based communication.
    - GraphQL for flexible, client-driven queries.
    - gRPC for high-performance, type-safe RPCs.
    - Server-Side Events (SSE) for real-time updates.
    - WebSockets for bidirectional communication.
- **Persistence Layer**:
    - **SQL Databases**: PostgreSQL, MySQL.
    - **NoSQL Databases**: Redis, MongoDB, Elasticsearch, Cassandra, InfluxDB, Neo4j.
- **API Management** with OAuth2 for secure, scalable access control.
- **Full Observability**: Advanced logging, monitoring, and tracing with tools like Prometheus, Grafana, and Jaeger.
- **Deployment Strategies**: Rolling Upgrades, Blue-Green, and Canary deployments via Kubernetes.

## Goals

The **Microservices** variant aims to:
- Demonstrate how to design, build, and deploy a distributed system with independent, scalable services.
- Showcase best practices for **JDK 24** and **Spring Boot 3** in a cloud-native, microservices context.
- Provide a robust example of event-driven architectures with messaging and streaming.
- Enable seamless observability and advanced deployment strategies for production-ready systems.

## Getting Started

### Prerequisites

- **JDK 24**: Ensure Java 24 is installed. [Download JDK 24](https://jdk.java.net/24/).
- **Maven**: For dependency management and building services.
- **Docker**: For containerizing microservices.
- **Kubernetes**: For orchestration (e.g., Minikube or a cloud provider like AWS EKS, GKE).
- **Messaging Systems**: RabbitMQ and Apache Kafka (can be run via Docker).
- **IDE**: IntelliJ IDEA, Eclipse, or any IDE with Spring Boot support.
- **Database Access**:
    - PostgreSQL/MySQL for SQL persistence.
    - Redis, MongoDB, Elasticsearch, Cassandra, InfluxDB, or Neo4j for NoSQL use cases.
    - 