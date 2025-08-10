# Account Module

## Overview

The `account` module is a self-contained component of the `catch-a-flight` modular monolith application, responsible for handling user account creation and related operations. It follows a **Domain-Driven Design (DDD)** approach within a modular monolith architecture, ensuring clear boundaries, encapsulation, and maintainability. The module is designed to be independent, communicating with other modules through well-defined interfaces and events, while adhering to Clean Architecture principles for separation of concerns.

This module encapsulates the business logic for creating user accounts, validating input, persisting account data, and publishing events to notify other parts of the system about account-related activities. It is built using **Spring Boot** and leverages annotations for dependency injection, logging, and transaction management.

## Architecture

The `account` module is structured as a **modular monolith** component, meaning it is a self-contained unit within the larger monolithic application. It adheres to the following architectural principles:

- **Modularity**: The module is encapsulated, exposing only necessary interfaces (`CreateAccountUseCase`) and communicating with other modules via events (e.g., `AccountCreated`, `AccountCreationFailed`). This minimizes direct dependencies and enforces loose coupling.
- **Domain-Driven Design (DDD)**: The module focuses on the "Account" bounded context, with clear domain entities (`Accountlucent`), use cases (`CreateAccountUseCase`), and domain events.
- **Clean Architecture**: The module separates concerns into layers, including use cases, domain models, persistence, and event publishing, ensuring that business logic remains independent of infrastructure concerns.
- **Event-Driven Communication**: Account-related activities trigger events that other modules can subscribe to, facilitating asynchronous communication within the monolith.

### Module Boundaries

The `account` module is responsible for:
- Validating user input for account creation.
- Creating and persisting user accounts.
- Publishing domain events for successful or failed account creation.
- Ensuring compliance with password policies and other business rules.

It does *not* handle:
- User authentication or session management (likely handled by a separate module, e.g., `auth`).
- User profile updates or other account-related operations beyond creation.

## Internals

### Key Components

The module is implemented in Java using Spring Boot, with the following key components:

1. **CreateAccountService** (`CreateAccountService.java`):
    - **Role**: Implements the `CreateAccountUseCase` interface, serving as the entry point for account creation.
    - **Annotations**:
        - `@Service`: Marks it as a Spring service component.
        - `@DomainService`: Indicates its role as a DDD domain service.
        - `@RequiredArgsConstructor`: Uses Lombok to inject dependencies.
        - `@Slf4j`: Enables logging via Lombok.
        - `@Transactional`: Ensures database operations are atomic.
    - **Functionality**:
        - Validates the `CreateAccountCommand` using `CreateAccountCommandValidator`.
        - Creates an `Account` entity via `AccountFactory`.
        - Persists the account using `CreateAccountRepository`.
        - Publishes events (`AccountCreated` or `AccountCreationFailed`) via `AccountEventPublisher`.
        - Handles exceptions (e.g., `PasswordPolicyException`, generic errors) and returns appropriate `CreateAccountResult` objects.

2. **CreateAccountCommandValidator** (Nested in `CreateAccountService.java`):
    - **Role**: Validates the `CreateAccountCommand` to ensure it meets business rules (e.g., valid email, password strength).
    - **Annotation**: `@Component` for Spring dependency injection.
    - **Functionality**: Returns an `InputValidationResult` (`Valid` or `NotValid` with a message). Currently, it returns `Valid` by default, but it can be extended to include specific validation logic.

3. **AccountFactory**:
    - **Role**: Creates `Account` entities based on input parameters (email, password, username).
    - **Purpose**: Encapsulates account creation logic, ensuring consistent construction of domain entities.

4. **CreateAccountRepository**:
    - **Role**: Handles persistence of `Account` entities to the database.
    - **Purpose**: Abstracts database operations, keeping the service layer independent of persistence details.

5. **AccountEventPublisher**:
    - **Role**: Publishes domain events (`AccountCreated`, `AccountCreationFailed`) to notify other modules.
    - **Purpose**: Enables asynchronous communication with other parts of the monolith (e.g., sending welcome emails or updating user profiles).

6. **Domain Models and Events**:
    - **Account**: Represents the core domain entity with attributes like `accountId`, `email`, `userName`, and `accountType`.
    - **CreateAccountCommand**: A data transfer object (DTO) containing input data for account creation (email, password, username).
    - **CreateAccountResult**: A sealed class (or equivalent) representing possible outcomes:
        - `Success`: Contains the created `accountId`.
        - `InputNotValid`: Indicates validation failure with a message.
        - `PasswordPolicyFailure`: Indicates a password policy violation.
        - `InternalFailure`: Handles unexpected errors.
    - **AccountCreated**: Event published on successful account creation, containing `accountId`, `userName`, `accountType`, and `email`.
    - **AccountCreationFailed**: Event published on failure, containing `userName`, `email`, and the failure reason.

### Flow of Account Creation

1. **Input**: The `createUser` method receives a `CreateAccountCommand` with email, password, and username.
2. **Validation**:
    - Checks if the command is null, throwing `IllegalArgumentException` if so.
    - Uses `CreateAccountCommandValidator` to validate input, returning `InputNotValid` if validation fails.
3. **Account Creation**:
    - Creates an `Account` entity using `AccountFactory`.
    - Persists the account via `CreateAccountRepository`.
4. **Event Publishing**:
    - Publishes `AccountCreated` on success or `AccountCreationFailed` on failure.
5. **Error Handling**:
    - Catches `PasswordPolicyException` for policy violations, returning `PasswordPolicyFailure`.
    - Catches generic exceptions, returning `InternalFailure`.
6. **Output**: Returns a `CreateAccountResult` indicating the outcome.
