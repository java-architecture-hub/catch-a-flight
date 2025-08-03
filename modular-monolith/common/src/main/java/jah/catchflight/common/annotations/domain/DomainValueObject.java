package jah.catchflight.common.annotations.domain;

/**
 * Marks a class as a value object in the domain-driven design (DDD) context.
 * <p>
 * A value object is an immutable object whose identity is defined by its attributes rather than
 * a unique identifier. Value objects are typically used to represent concepts in the domain that
 * have no distinct lifecycle but are meaningful based on their data.
 * </p>
 * <p>
 * Classes annotated with {@code @DomainValueObject} are used to model attributes or measurements
 * that are significant in the domain, ensuring immutability and equality based on their values.
 * </p>
 */
public @interface DomainValueObject {}
