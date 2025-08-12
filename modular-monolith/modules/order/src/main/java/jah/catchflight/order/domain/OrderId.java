package jah.catchflight.order.domain;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.Objects;
import java.util.UUID;

@DomainValueObject
public record OrderId(UUID id) {
    public OrderId {
        Objects.requireNonNull(id);
    }
}
