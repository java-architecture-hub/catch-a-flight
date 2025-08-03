package jah.catchflight.common.annotations.event;

/**
 * Marks a class as a domain event in the event-driven architecture of the CatchFlight application.
 * <p>
 * A domain event represents a significant occurrence or state change within the domain that is
 * relevant to the business logic. Classes annotated with {@code @Event} are typically used to
 * capture and communicate these occurrences across different parts of the application or to
 * external systems, enabling decoupled and reactive processing.
 * </p>
 *
 * @since 1.0
 */
public @interface Event {
}
