package jah.catchflight.sharedkernel.query;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.Objects;

/**
 * A record representing an airport identified by its IATA code.
 *
 * @param code the IATA code of the airport, must not be null
 */
@DomainValueObject
public record Airport(IATACode code) {
    public Airport {
        Objects.requireNonNull(code, "IATA code must not be null");
    }
}
