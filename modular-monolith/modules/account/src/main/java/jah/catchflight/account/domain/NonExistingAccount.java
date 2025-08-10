package jah.catchflight.account.domain;

import jah.catchflight.common.annotations.domain.DomainValueObject;

/**
 * A domain value object representing a non-existing account.
 * This record implements the {@link CurrentAccount} interface and indicates that no account exists
 * for a given query or context.
 */
@DomainValueObject
public record NonExistingAccount() implements CurrentAccount {}
