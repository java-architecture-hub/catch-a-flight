package jah.catchflight.order.domain;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.Objects;

/**
 * A record representing user details, including their name and address.
 *
 * @param firstName the user's first name, must not be null
 * @param lastName  the user's last name, must not be null
 * @param address   the user's address, must not be null
 */
@DomainValueObject
public record UserDetails(
        String firstName,
        String lastName,
        Address address) {
    public UserDetails {
        Objects.requireNonNull(firstName, "First name must not be null");
        Objects.requireNonNull(lastName, "Last name must not be null");
        Objects.requireNonNull(address, "Address must not be null");
    }
}
