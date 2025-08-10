package jah.catchflight.query.domain;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.Objects;
import java.util.UUID;

@DomainValueObject
public record QueryId(UUID id) {
    public QueryId {
        Objects.requireNonNull(id);
    }
}
