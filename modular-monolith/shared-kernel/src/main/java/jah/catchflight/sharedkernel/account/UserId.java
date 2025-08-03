package jah.catchflight.sharedkernel.account;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.UUID;

/**
 * Represents a unique identifier for a user in the CatchFlight application.
 * This record encapsulates a UUID as a domain value object, ensuring a consistent
 * and type-safe representation of user identities across the system.
 *
 * @param value the UUID representing the unique user identifier
 * @since 1.0
 */
@DomainValueObject
public record UserId(UUID value) {}
