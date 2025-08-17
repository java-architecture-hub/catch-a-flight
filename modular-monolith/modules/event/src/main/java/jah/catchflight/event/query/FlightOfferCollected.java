package jah.catchflight.event.query;

import jah.catchflight.common.events.DomainEvent;
import jah.catchflight.sharedkernel.query.Offer;

import java.util.UUID;

/**
 * A record representing a domain event for a collected flight offer.
 * <p>
 * This event is triggered when a flight offer is successfully collected and encapsulates
 * the event's unique identifier and the associated {@link Offer} details.
 * </p>
 * Implements the {@link DomainEvent} interface to integrate with the domain event system.
 *
 * @param eventId the unique identifier of the event
 * @param offer   the flight offer associated with this event
 */
public record FlightOfferCollected(UUID eventId, Offer offer) implements DomainEvent {}
