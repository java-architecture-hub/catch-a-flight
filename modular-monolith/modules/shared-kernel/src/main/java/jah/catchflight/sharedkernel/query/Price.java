package jah.catchflight.sharedkernel.query;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A record representing a price with an amount and currency.
 */
@DomainValueObject
public record Price(BigDecimal value, Currency currency) {
    public Price {
        Objects.requireNonNull(value, "Price value must not be null");
        Objects.requireNonNull(currency, "Currency must not be null");
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price value must be positive");
        }
    }
}
