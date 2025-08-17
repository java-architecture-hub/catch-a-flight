package jah.catchflight.event.account;

import jah.catchflight.common.events.DomainEvent;
import jah.catchflight.sharedkernel.account.AccountId;
import jah.catchflight.sharedkernel.account.AccountType;
import jah.catchflight.sharedkernel.account.Email;
import jah.catchflight.sharedkernel.account.UserName;

import java.util.Objects;
import java.util.UUID;

/**
 * A record representing a domain event for a successfully created user account.
 * This event is triggered when a new account is created in the system, capturing
 * essential details about the account. It implements the {@link DomainEvent} interface
 * to integrate with the system's event-driven architecture.
 *
 * @param eventId     the unique identifier of the event
 * @param accountId   the unique identifier of the created user account
 * @param userName    the username associated with the account
 * @param accountType the type of account created
 * @param email       the email address associated with the account
 */
public record AccountCreated(UUID eventId, AccountId accountId, UserName userName, AccountType accountType,
                             Email email) implements DomainEvent {
    public AccountCreated {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(accountId);
        Objects.requireNonNull(userName);
        Objects.requireNonNull(accountType);
        Objects.requireNonNull(email);
    }
}
