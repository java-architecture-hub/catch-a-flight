package jah.catchflight.account.domain.events;

import jah.catchflight.account.domain.model.UserName;
import jah.catchflight.common.events.DomainEvent;
import jah.catchflight.sharedkernel.account.AccountType;
import jah.catchflight.sharedkernel.account.Email;
import jah.catchflight.sharedkernel.account.UserId;

import java.util.Objects;
import java.util.UUID;

/**
 * A record representing a domain event for a successfully created user account.
 * This event is triggered when a new account is created in the system, capturing
 * essential details about the account. It implements the {@link DomainEvent} interface
 * to integrate with the system's event-driven architecture.
 *
 * @param eventId     the unique identifier of the event
 * @param userId      the unique identifier of the created user account
 * @param userName    the username associated with the account
 * @param accountType the type of account created
 * @param email       the email address associated with the account
 */
public record AccountCreated(UUID eventId, UserId userId, UserName userName, AccountType accountType,
                             Email email) implements DomainEvent {
    /**
     * Constructs a new {@link AccountCreated} event with validation.
     * Ensures that all provided parameters are non-null to maintain event integrity.
     *
     * @param eventId     the unique identifier of the event, must not be null
     * @param userId      the unique identifier of the user account, must not be null
     * @param userName    the username, must not be null
     * @param accountType the account type, must not be null
     * @param email       the email address, must not be null
     * @throws NullPointerException if any parameter is null
     */
    public AccountCreated {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(userId);
        Objects.requireNonNull(userName);
        Objects.requireNonNull(accountType);
        Objects.requireNonNull(email);
    }
}
