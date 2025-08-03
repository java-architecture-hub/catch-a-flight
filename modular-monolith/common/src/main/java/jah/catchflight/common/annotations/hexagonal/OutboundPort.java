package jah.catchflight.common.annotations.hexagonal;

/**
 * Marks an interface as an outbound port in the hexagonal architecture (Ports and Adapters) of the
 * CatchFlight application.
 * <p>
 * An outbound port defines the contract for operations that the application's core business logic
 * can invoke to interact with external systems (e.g., databases, APIs, or messaging services).
 * It provides a decoupled interface that outbound adapters implement to connect the domain logic
 * to external dependencies.
 * </p>
 *
 * @since 1.0
 */
public @interface OutboundPort {}
