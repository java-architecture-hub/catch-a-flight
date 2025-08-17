package jah.catchflight.common.events;

/**
 * Defines a contract for publishing domain events in the CatchFlight application.
 * Implementations of this interface are responsible for dispatching {@link DomainEvent}
 * instances to relevant event handlers or messaging systems, facilitating event-driven
 * communication within the application.
 */
public interface DomainEventsPublisher {
    /**
     * Publishes a domain event to be processed by registered event handlers or
     * messaging systems.
     */
    void publish(DomainEvent event);
}
