package jah.catchflight.account.domain.model;

import jah.catchflight.common.annotations.domain.DomainValueObject;

/**
 * A domain value object representing the current state of an account.
 * This sealed interface defines the possible states of an account, which can be a regular account,
 * a premium account, or a non-existing account.
 */
@DomainValueObject
public sealed interface CurrentAccount permits RegularAccount, PremiumAccount, NonExistingAccount {}
