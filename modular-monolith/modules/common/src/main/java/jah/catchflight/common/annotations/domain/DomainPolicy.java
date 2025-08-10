package jah.catchflight.common.annotations.domain;

/**
 * Marks a class as a domain policy in the domain-driven design (DDD) context.
 * <p>
 * A domain policy encapsulates business rules or logic that governs the behavior of domain objects
 * or aggregates, often defining how certain actions or decisions should be handled within the domain.
 * </p>
 * <p>
 * Classes annotated with {@code @DomainPolicy} are typically used to centralize and enforce
 * complex business rules, ensuring consistency and adherence to the domain's requirements.
 * </p>
 */
public @interface DomainPolicy {}
