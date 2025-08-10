package jah.catchflight.account.event;

import jah.catchflight.common.events.DomainEvent;
import jah.catchflight.sharedkernel.account.UserId;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a domain event indicating that an attempt to upgrade a user's account has failed.
 * This event captures the unique event identifier, the user identifier, and a message describing
 * the reason for the failure.
 */
public record AccountUpgradeFailed(UUID eventId, UserId userId, String message) implements DomainEvent {
    public AccountUpgradeFailed {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(userId);
        Objects.requireNonNull(message);
    }
}
