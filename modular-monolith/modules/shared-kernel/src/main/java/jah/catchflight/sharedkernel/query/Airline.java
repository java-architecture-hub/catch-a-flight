package jah.catchflight.sharedkernel.query;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.Objects;

/**
 * A record representing an airline identified by its name.
 *
 * @param name the name of the airline, must not be null
 */
@DomainValueObject
public record Airline(String name) {
    public Airline {
        Objects.requireNonNull(name, "Airline name must not be null");
    }
}
