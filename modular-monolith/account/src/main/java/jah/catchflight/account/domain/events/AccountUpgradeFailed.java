package jah.catchflight.account.domain.events;

import jah.catchflight.common.events.DomainEvent;
import jah.catchflight.sharedkernel.account.UserId;

import java.util.Objects;
import java.util.UUID;

public record AccountUpgradeFailed(UUID eventId, UserId userId, String message)
        implements DomainEvent {
    public AccountUpgradeFailed {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(userId);
        Objects.requireNonNull(message);
    }
}
