package jah.catchflight.query.domain;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.time.Instant;
import java.util.Objects;

/**
 * A record representing an expiration time for an offer or event.
 *
 * @param time the instant representing the expiration time, must not be null
 */
@DomainValueObject
public record ExpirationTime(Instant time) {
    public ExpirationTime {
        Objects.requireNonNull(time, "Expiration time must not be null");
    }
}
