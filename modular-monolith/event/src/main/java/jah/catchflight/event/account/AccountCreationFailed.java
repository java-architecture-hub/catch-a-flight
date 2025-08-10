package jah.catchflight.event.account;

import jah.catchflight.common.events.DomainEvent;
import jah.catchflight.sharedkernel.account.Email;
import jah.catchflight.sharedkernel.account.UserName;

import java.util.Objects;
import java.util.UUID;

/**
 * A record representing a domain event for a failed account creation attempt.
 * This event is triggered when an attempt to create a new user account fails,
 * capturing details about the failure. It implements the {@link DomainEvent} interface
 * to integrate with the system's event-driven architecture.
 *
 * @param eventId  the unique identifier of the event
 * @param userName the username associated with the failed account creation attempt
 * @param email    the email address associated with the failed account creation attempt
 * @param message  the error message describing the reason for the failure
 */
public record AccountCreationFailed(UUID eventId, UserName userName, Email email, String message)
        implements DomainEvent {
    /**
     * Constructs a new {@link AccountCreationFailed} event with validation.
     * Ensures that all provided parameters are non-null to maintain event integrity.
     *
     * @param eventId  the unique identifier of the event, must not be null
     * @param userName the username, must not be null
     * @param email    the email address, must not be null
     * @param message  the error message, must not be null
     * @throws NullPointerException if any parameter is null
     */
    public AccountCreationFailed {
        Objects.requireNonNull(eventId);
        Objects.requireNonNull(userName);
        Objects.requireNonNull(email);
        Objects.requireNonNull(message);
    }
}
