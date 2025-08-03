package jah.catchflight.account.domain.model;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.Objects;

@DomainValueObject
public record Password(String value) {
    public Password {
        Objects.requireNonNull(value);
    }
}