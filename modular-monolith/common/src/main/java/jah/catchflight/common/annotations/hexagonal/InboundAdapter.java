package jah.catchflight.common.annotations.hexagonal;

/**
 * Marks a class as an inbound adapter in the hexagonal architecture (Ports and Adapters) of the
 * CatchFlight application.
 * <p>
 * An inbound adapter, also known as a driving adapter, is responsible for receiving external
 * inputs (e.g., from user interfaces, APIs, or other external systems) and translating them
 * into calls to the application's inbound ports. This ensures a decoupled interface between
 * external systems and the core business logic.
 * </p>
 *
 * @since 1.0
 */
public @interface InboundAdapter {}
