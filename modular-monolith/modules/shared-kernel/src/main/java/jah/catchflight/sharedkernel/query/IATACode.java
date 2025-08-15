package jah.catchflight.sharedkernel.query;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.Objects;

/**
 * A record representing an IATA code, a three-letter code identifying an airport.
 *
 * @param value the IATA code as a string, must not be null
 */
@DomainValueObject
public record IATACode(String value) {
    public IATACode {
        Objects.requireNonNull(value, "IATA code value must not be null");
    }
}
