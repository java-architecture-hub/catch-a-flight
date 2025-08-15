package jah.catchflight.order.domain;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.Objects;

/**
 * A record representing a physical address with street, city, and zip code.
 *
 * @param street   the street name and number, must not be null
 * @param city     the city name, must not be null
 * @param zipCode  the zip code, must not be null
 */
@DomainValueObject
public record Address(String street, String city, String zipCode) {
    public Address {
        Objects.requireNonNull(street, "Street must not be null");
        Objects.requireNonNull(city, "City must not be null");
        Objects.requireNonNull(zipCode, "Zip code must not be null");
    }
}
