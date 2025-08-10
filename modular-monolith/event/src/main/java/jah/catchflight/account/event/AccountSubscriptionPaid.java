package jah.catchflight.account.event;

import jah.catchflight.common.events.DomainEvent;

import java.util.UUID;

/**
 * Represents a domain event indicating that an account's subscription payment has been successfully processed.
 * This record implements the {@link DomainEvent} interface and contains the unique event identifier.
 *
 * @param eventId the unique identifier of the event
 */
public record AccountSubscriptionPaid(UUID eventId) implements DomainEvent {
    @Override
    public UUID eventId() {
        return eventId;
    }
}
