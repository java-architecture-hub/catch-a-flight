package jah.catchflight.common.events;

import java.util.UUID;

/**
 * Represents a domain event in the CatchFlight application, encapsulating significant
 * occurrences or state changes within the domain. Implementations of this interface
 * are used to communicate events across different parts of the application, typically
 * in an event-driven architecture.
 *
 * @since 1.0
 */
public interface DomainEvent {
    /**
     * Retrieves the unique identifier for this domain event.
     *
     * @return the {@link UUID} representing the unique event ID
     */
    UUID eventId();
}
