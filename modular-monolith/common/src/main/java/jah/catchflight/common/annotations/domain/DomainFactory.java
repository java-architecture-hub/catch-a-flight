package jah.catchflight.common.annotations.domain;

/**
 * Marks a class as a factory for creating domain objects in the domain-driven design (DDD) context.
 * <p>
 * A factory is responsible for encapsulating the logic required to create complex domain objects
 * or aggregates, ensuring that they are instantiated in a valid state according to business rules.
 * </p>
 * <p>
 * Classes annotated with {@code @DomainFactory} are typically used to centralize object creation
 * logic, improving maintainability and consistency within the domain model.
 * </p>
 *
 * @since 1.0
 */
public @interface DomainFactory {
}
