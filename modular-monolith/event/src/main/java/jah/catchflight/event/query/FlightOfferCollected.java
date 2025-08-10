package jah.catchflight.event.query;

import jah.catchflight.common.events.DomainEvent;

import java.util.UUID;

public record FlightOfferCollected(UUID eventId) implements DomainEvent {}
