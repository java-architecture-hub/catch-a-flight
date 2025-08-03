package jah.catchflight.account.domain.events;

import jah.catchflight.common.events.DomainEvent;

import java.util.UUID;

/**
 * Represents a domain event indicating that a new account has been created.
 * This record implements the {@link DomainEvent} interface and contains the unique event identifier.
 *
 * @param eventId the unique identifier of the event
 */
public record AccountCreated(UUID eventId) implements DomainEvent {
    /**
     * Returns the unique identifier of the account creation event.
     *
     * @return the event's unique identifier
     */
    @Override
    public UUID eventId() {
        return eventId;
    }
}

