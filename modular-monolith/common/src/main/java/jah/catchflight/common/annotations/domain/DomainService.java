package jah.catchflight.common.annotations.domain;

/**
 * Marks a class as a domain service in the domain-driven design (DDD) context.
 * <p>
 * A domain service encapsulates business logic that does not naturally belong to a specific domain
 * entity or aggregate, often coordinating operations across multiple domain objects or providing
 * stateless functionality to support the domain model.
 * </p>
 * <p>
 * Classes annotated with {@code @DomainService} are typically used to implement complex business
 * processes or rules that span multiple entities, ensuring clear separation of concerns within the
 * domain model.
 * </p>
 */
public @interface DomainService {}
