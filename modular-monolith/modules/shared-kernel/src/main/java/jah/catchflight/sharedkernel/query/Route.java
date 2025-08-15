package jah.catchflight.sharedkernel.query;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.Objects;

/**
 * A record representing a travel route between two airports with departure and return time ranges.
 *
 * @param from       the departure airport, must not be null
 * @param to         the destination airport, must not be null
 * @param time       the departure time range, must not be null
 * @param returnTime the return time range, must not be null
 */
@DomainValueObject
public record Route(
        Airport from,
        Airport to,
        DateTimeRange time,
        DateTimeRange returnTime) {
    public Route {
        Objects.requireNonNull(from, "Departure airport must not be null");
        Objects.requireNonNull(to, "Destination airport must not be null");
        Objects.requireNonNull(time, "Departure time range must not be null");
        Objects.requireNonNull(returnTime, "Return time range must not be null");
    }
}
