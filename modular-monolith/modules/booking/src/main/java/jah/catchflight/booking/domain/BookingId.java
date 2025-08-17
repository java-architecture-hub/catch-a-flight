package jah.catchflight.booking.domain;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.UUID;

/**
 * A domain value object representing a unique identifier for a flight booking.
 */
@DomainValueObject
public record BookingId(UUID value) {}
