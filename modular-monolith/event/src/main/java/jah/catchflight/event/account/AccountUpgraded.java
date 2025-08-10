package jah.catchflight.event.account;

import jah.catchflight.common.events.DomainEvent;
import jah.catchflight.sharedkernel.account.AccountId;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a domain event indicating that an account has been successfully upgraded in the CatchFlight application.
 * This record implements the {@link DomainEvent} interface and encapsulates the event's unique identifier and the
 * {@link AccountId} of the upgraded account.
 *
 * @param eventId the unique identifier of the event
 * @param accountId  the unique identifier of the account that was upgraded
 * @since 1.0
 */
public record AccountUpgraded(UUID eventId, AccountId accountId) implements DomainEvent {
    public AccountUpgraded {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(accountId);
    }
}
