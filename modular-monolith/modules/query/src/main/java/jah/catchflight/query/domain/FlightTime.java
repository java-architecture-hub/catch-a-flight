package jah.catchflight.query.domain;

import java.time.OffsetDateTime;
import java.util.Objects;

public record FlightTime(OffsetDateTime time) {
    public FlightTime {
        Objects.requireNonNull(time);
    }
}

