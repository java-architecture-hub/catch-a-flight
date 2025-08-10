package jah.catchflight.sharedkernel.query;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.Objects;

@DomainValueObject
public record IATACode(String value) {
    public IATACode {
        Objects.requireNonNull(value);
    }
}
