package jah.catchflight.booking.domain;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.UUID;

/**
 * A domain value object representing a unique identifier for a flight booking.
 * <p>
 * This record encapsulates a {@link UUID} to uniquely identify a booking in the system.
 * It is annotated with {@code @DomainValueObject} to indicate its role as an immutable
 * value object in the domain model, typically used in conjunction with services like
 * {@link BookFlightsService} or events like {@link FlightOfferCollected}.
 * </p>
 *
 * @param value the unique identifier for the booking
 * @see BookFlightsService
 * @see FlightOfferCollected
 */
@DomainValueObject
public record BookingId(UUID value) {}
