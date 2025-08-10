package jah.catchflight.account.domain.model;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.Objects;

/**
 * A domain value object representing a user's password.
 * This record encapsulates the password string and ensures it is not null.
 *
 * @param value the password string
 */
@DomainValueObject
public record Password(String value) {}
