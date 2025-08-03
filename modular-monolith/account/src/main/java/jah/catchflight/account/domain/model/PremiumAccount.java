package jah.catchflight.account.domain.model;

import jah.catchflight.common.annotations.domain.DomainValueObject;
import jah.catchflight.sharedkernel.account.UserId;

/**
 * A domain value object representing a premium account.
 * This record implements the {@link CurrentAccount} interface and contains the unique identifier
 * of a premium account.
 *
 * @param userId the unique identifier of the premium account
 */
@DomainValueObject
public record PremiumAccount(UserId userId) implements CurrentAccount {}
