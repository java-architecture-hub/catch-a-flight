package jah.catchflight.account.domain.events;

import jah.catchflight.common.events.DomainEvent;

import java.util.UUID;

/**
 * Represents a domain event indicating that an account has been deleted.
 * This record implements the {@link DomainEvent} interface and contains the unique event identifier.
 *
 * @param eventId the unique identifier of the event
 */
public record AccountDeleted(UUID eventId) implements DomainEvent {
    @Override
    public UUID eventId() {
        return eventId;
    }
}
