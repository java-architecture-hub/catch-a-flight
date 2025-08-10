package jah.catchflight.common.annotations.hexagonal;

/**
 * Marks a class as an outbound adapter in the hexagonal architecture (Ports and Adapters) of the
 * CatchFlight application.
 * <p>
 * An outbound adapter, also known as a driven adapter, is responsible for translating calls from
 * the application's outbound ports into interactions with external systems (e.g., databases,
 * APIs, or messaging services). This ensures a decoupled interface between the core business
 * logic and external dependencies.
 * </p>
 */
public @interface OutboundAdapter {}
