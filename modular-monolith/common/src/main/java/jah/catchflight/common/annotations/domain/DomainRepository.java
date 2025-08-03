package jah.catchflight.common.annotations.domain;

/**
 * Marks a class as a repository for managing domain objects in the domain-driven design (DDD) context.
 * <p>
 * A repository acts as an interface to persist and retrieve domain objects or aggregates, abstracting
 * the underlying data storage and providing a collection-like interface for accessing domain entities.
 * </p>
 * <p>
 * Classes annotated with {@code @DomainRepository} are responsible for encapsulating data access logic,
 * ensuring that domain objects are retrieved and stored in a consistent manner.
 * </p>
 *
 * @since 1.0
 */
public @interface DomainRepository {}
