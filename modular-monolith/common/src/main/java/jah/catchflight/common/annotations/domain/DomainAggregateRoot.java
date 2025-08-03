package jah.catchflight.common.annotations.domain;

/**
 * Marks a class as a domain aggregate root in the domain-driven design (DDD) context.
 * <p>
 * An aggregate root is an entity that acts as the primary point of interaction for a
 * cohesive group of related objects, ensuring consistency and enforcing business rules
 * within the aggregate's boundary.
 * </p>
 * <p>
 * Classes annotated with {@code @DomainAggregate} are typically responsible for
 * coordinating the behavior of the aggregate and maintaining its invariants.
 * </p>
 *
 * @since 1.0
 */
public @interface DomainAggregateRoot {
}
