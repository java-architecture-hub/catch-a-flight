package jah.catchflight.query.domain;

import java.time.Instant;
import java.util.Objects;

public record ExpirationTime(Instant time) {
    public ExpirationTime {
        Objects.requireNonNull(time);
    }
}
