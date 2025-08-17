package jah.catchflight.sharedkernel.query;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * A record representing a time range defined by a start and end date-time with offset.
 */
@DomainValueObject
public record DateTimeRange(
        OffsetDateTime begin,
        OffsetDateTime end) {
    public DateTimeRange {
        Objects.requireNonNull(begin, "Begin date-time must not be null");
        Objects.requireNonNull(end, "End date-time must not be null");
    }
}
