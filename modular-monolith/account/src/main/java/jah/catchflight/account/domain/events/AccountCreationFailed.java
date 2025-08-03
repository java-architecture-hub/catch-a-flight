package jah.catchflight.account.domain.events;

import jah.catchflight.common.events.DomainEvent;

import java.util.UUID;

public record AccountCreationFailed(UUID eventId) implements DomainEvent {
    @Override
    public UUID eventId() {
        return eventId;
    }
}
