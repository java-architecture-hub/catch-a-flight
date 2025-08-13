package jah.catchflight.query.domain;

import jah.catchflight.sharedkernel.query.OfferId;

public record Offer(
        OfferId offerId,
        Airline airline,
        FlightTime flightTime,
        ExpirationTime expirationTime,
        Price price) {
}
