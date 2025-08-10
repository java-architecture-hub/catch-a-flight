package jah.catchflight.account.domain.model;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.Objects;

/**
 * A domain value object representing a user's full name.
 * This record encapsulates the first name and last name of a user, ensuring both are non-null.
 *
 * @param firstName the user's first name
 * @param lastName  the user's last name
 */
@DomainValueObject
public record UserName(String firstName, String lastName) {}
