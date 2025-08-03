package jah.catchflight.account.domain.model;

import jah.catchflight.common.annotations.domain.DomainValueObject;
import jah.catchflight.sharedkernel.account.UserId;

@DomainValueObject
public record RegularAccount(UserId userId) implements CurrentAccount {}
