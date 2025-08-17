package jah.catchflight.sharedkernel.account;

import jah.catchflight.common.annotations.domain.DomainValueObject;

/**
 * A domain value object representing a user's full name.
 * This record encapsulates the first name and last name of a user, ensuring both are non-null.
 */
@DomainValueObject
public record UserName(String firstName, String lastName) {}
