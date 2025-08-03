package jah.catchflight.common.annotations.hexagonal;

/**
 * Marks an interface as an inbound port in the hexagonal architecture (Ports and Adapters) of the
 * CatchFlight application.
 * <p>
 * An inbound port defines the contract for operations that external systems can invoke on the
 * application's core business logic. It acts as an entry point, allowing inbound adapters to
 * interact with the domain logic in a decoupled manner.
 * </p>
 *
 * @since 1.0
 */
public @interface InboundPort {}
