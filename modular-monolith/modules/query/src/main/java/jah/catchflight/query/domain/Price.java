package jah.catchflight.query.domain;

import java.math.BigDecimal;
import java.util.Objects;

public record Price(BigDecimal value, Currency currency) {
    public Price {
        Objects.requireNonNull(value);
        Objects.requireNonNull(currency);
    }
}

