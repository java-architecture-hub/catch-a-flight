package jah.catchflight.sharedkernel.query;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.Objects;
import java.util.UUID;

/**
 * A record representing a unique identifier for an offer, using a UUID.
 */
@DomainValueObject
public record OfferId(UUID value) {
    public OfferId {
        Objects.requireNonNull(value, "UUID value must not be null");
    }
}