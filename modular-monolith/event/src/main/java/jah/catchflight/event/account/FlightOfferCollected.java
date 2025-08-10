package jah.catchflight.event.account;

import jah.catchflight.common.events.DomainEvent;

import java.util.UUID;

public record FlightOfferCollected(UUID eventId) implements DomainEvent {}
