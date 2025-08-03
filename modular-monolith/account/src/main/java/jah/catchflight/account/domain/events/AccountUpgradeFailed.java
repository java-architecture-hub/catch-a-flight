package jah.catchflight.account.domain.events;

import jah.catchflight.common.events.DomainEvent;

import java.util.UUID;

/**
 * Represents a domain event indicating that an account upgrade attempt has failed.
 * This record implements the {@link DomainEvent} interface and contains the unique event identifier.
 *
 * @param eventId the unique identifier of the event
 */
public record AccountUpgradeFailed(UUID eventId) implements DomainEvent {

    /**
     * Returns the unique identifier of the failed account upgrade event.
     *
     * @return the event's unique identifier
     */
    @Override
    public UUID eventId() {
        return eventId;
    }
}
