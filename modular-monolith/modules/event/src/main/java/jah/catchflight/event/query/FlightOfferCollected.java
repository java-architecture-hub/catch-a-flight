package jah.catchflight.event.query;

import jah.catchflight.common.events.DomainEvent;

import java.util.UUID;

/**
 * A domain event representing the collection of a flight offer.
 * <p>
 * This record is used to signal that a flight offer has been successfully collected
 * in an event-driven system. It includes a unique identifier for the event.
 * </p>
 *
 * @param eventId the unique identifier of the event
 */
public record FlightOfferCollected(UUID eventId) implements DomainEvent {}
