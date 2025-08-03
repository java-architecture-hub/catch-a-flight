package jah.catchflight.account.domain.model;

import jah.catchflight.common.annotations.domain.DomainValueObject;

@DomainValueObject
public sealed interface CurrentAccount permits RegularAccount, PremiumAccount, NonExistingAccount {}
