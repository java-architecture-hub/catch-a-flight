package jah.catchflight.account.domain;

import jah.catchflight.common.annotations.domain.DomainValueObject;
import jah.catchflight.sharedkernel.account.AccountId;

/**
 * A domain value object representing a regular account.
 * This record implements the {@link CurrentAccount} interface and contains the unique identifier
 * of a regular account.
 *
 * @param accountId the unique identifier of the regular account
 */
@DomainValueObject
public record RegularAccount(AccountId accountId) implements CurrentAccount {}
