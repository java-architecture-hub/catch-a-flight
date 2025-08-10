package jah.catchflight.booking.domain;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.UUID;

@DomainValueObject
public record BookingId(UUID value) {}
