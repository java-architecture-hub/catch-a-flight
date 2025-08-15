package jah.catchflight.sharedkernel.query;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.Objects;
import java.util.UUID;

/**
 * A record representing a unique identifier for an offer, using a UUID.
 *
 * @param value the UUID value of the offer identifier, must not be null
 */
@DomainValueObject
public record OfferId(UUID value) {
    public OfferId {
        Objects.requireNonNull(value, "UUID value must not be null");
    }
}