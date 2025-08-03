package jah.catchflight.account.domain.events;

import jah.catchflight.common.events.DomainEvent;
import jah.catchflight.sharedkernel.account.UserId;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a domain event indicating that an account has been successfully upgraded in the CatchFlight application.
 * This record implements the {@link DomainEvent} interface and encapsulates the event's unique identifier and the
 * {@link UserId} of the upgraded account.
 *
 * @param eventId the unique identifier of the event
 * @param userId  the unique identifier of the account that was upgraded
 * @since 1.0
 */
public record AccountUpgraded(UUID eventId, UserId userId) implements DomainEvent {

    /**
     * Constructs an {@code AccountUpgraded} event with the specified event ID and user ID.
     * Ensures that both parameters are non-null to maintain event integrity.
     *
     * @param eventId the unique identifier of the event
     * @param userId  the unique identifier of the account that was upgraded
     * @throws NullPointerException if either {@code eventId} or {@code userId} is null
     * @since 1.0
     */
    public AccountUpgraded {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(userId);
    }
}
