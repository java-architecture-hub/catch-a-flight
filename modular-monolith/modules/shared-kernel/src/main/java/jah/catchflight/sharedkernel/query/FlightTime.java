package jah.catchflight.sharedkernel.query;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * A record representing the scheduled time of a flight.
 */
@DomainValueObject
public record FlightTime(OffsetDateTime time) {
    public FlightTime {
        Objects.requireNonNull(time, "Flight time must not be null");
    }
}
