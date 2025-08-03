package jah.catchflight.account.domain.model;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.Objects;

@DomainValueObject
public record UserName(String firstName, String lastName) {
    public UserName {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
    }
}

