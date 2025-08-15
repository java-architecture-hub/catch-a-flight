package jah.catchflight.query.domain;

import jah.catchflight.common.annotations.domain.DomainValueObject;
import jah.catchflight.sharedkernel.query.OfferId;

import java.util.Objects;

/**
 * A record representing an offer for a flight, including its identifier, airline, flight time, expiration, and price.
 *
 * @param offerId        the unique identifier of the offer, must not be null
 * @param airline        the airline providing the offer, must not be null
 * @param flightTime     the scheduled flight time, must not be null
 * @param expirationTime the expiration time of the offer, must not be null
 * @param price          the price of the offer, must not be null
 */
@DomainValueObject
public record Offer(
        OfferId offerId,
        Airline airline,
        FlightTime flightTime,
        ExpirationTime expirationTime,
        Price price) {
    /**
     * Creates an instance of {@code Offer} with the specified identifier, airline, flight time, expiration, and price.
     *
     * @param offerId        the unique identifier of the offer, must not be null
     * @param airline        the airline providing the offer, must not be null
     * @param flightTime     the scheduled flight time, must not be null
     * @param expirationTime the expiration time of the offer, must not be null
     * @param price          the price of the offer, must not be null
     * @throws NullPointerException if any of the parameters are null
     */
    public Offer {
        Objects.requireNonNull(offerId, "Offer ID must not be null");
        Objects.requireNonNull(airline, "Airline must not be null");
        Objects.requireNonNull(flightTime, "Flight time must not be null");
        Objects.requireNonNull(expirationTime, "Expiration time must not be null");
        Objects.requireNonNull(price, "Price must not be null");
    }
}
