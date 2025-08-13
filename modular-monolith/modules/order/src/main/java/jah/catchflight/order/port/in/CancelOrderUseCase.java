package jah.catchflight.order.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.order.domain.OrderId;

/**
 * Defines a use case for canceling orders in the system.
 * This interface provides a contract for preparing an order for cancellation based on the provided
 * command, encapsulating the business logic for order cancellation.
 */
@InboundPort
public interface CancelOrderUseCase {
    /**
     * Prepares an order for cancellation based on the provided command.
     *
     * @param prepareOrderCommand the command containing details required to prepare the order for cancellation
     * @return a {@link CancelOrderResult} indicating the outcome of the order cancellation preparation
     */
    CancelOrderResult prepareOrder(CancelOrderCommand prepareOrderCommand);

    /**
     * Sealed interface representing the result of an order cancellation preparation attempt.
     */
    public sealed interface CancelOrderResult {
        record Success() implements CancelOrderResult {}
        record InternalFailure(Throwable cause) implements CancelOrderResult {}
    }

    /**
     * Record representing the command to prepare an order for cancellation.
     */
    record CancelOrderCommand(OrderId orderId) {}
}
