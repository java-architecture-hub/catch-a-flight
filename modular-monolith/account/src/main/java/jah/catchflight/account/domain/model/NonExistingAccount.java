package jah.catchflight.account.domain.model;

import jah.catchflight.common.annotations.domain.DomainValueObject;

@DomainValueObject
public record NonExistingAccount() implements CurrentAccount {}
