package jah.catchflight.sharedkernel.account;

import jah.catchflight.common.annotations.domain.DomainValueObject;

/**
 * Represents an email address as a domain value object in the CatchFlight application.
 * This record encapsulates the email address value, ensuring it adheres to domain-specific
 * validation rules and constraints.
 *
 * @param value the string representation of the email address
 */
@DomainValueObject
public record Email(String value) {}
