package jah.catchflight.account.domain.model;

import jah.catchflight.common.annotations.domain.DomainValueObject;
import jah.catchflight.sharedkernel.account.UserId;

/**
 * A domain value object representing a regular account.
 * This record implements the {@link CurrentAccount} interface and contains the unique identifier
 * of a regular account.
 *
 * @param userId the unique identifier of the regular account
 */
@DomainValueObject
public record RegularAccount(UserId userId) implements CurrentAccount {}
