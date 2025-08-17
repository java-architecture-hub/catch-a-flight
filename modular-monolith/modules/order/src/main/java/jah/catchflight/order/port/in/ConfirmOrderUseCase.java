package jah.catchflight.order.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.order.domain.OrderId;

/**
 * Defines a use case for confirming orders in the system.
 * This interface provides a contract for preparing an order for confirmation based on the provided
 * command, encapsulating the business logic for order confirmation.
 */
@InboundPort
public interface ConfirmOrderUseCase {

    /**
     * Prepares an order for confirmation based on the provided command.
     */
    PrepareOrderResult prepareOrder(PrepareOrderCommand prepareOrderCommand);

    /**
     * Sealed interface representing the result of an order confirmation preparation attempt.
     */
    public sealed interface PrepareOrderResult {
        record Success() implements PrepareOrderResult {}
        record InternalFailure(Throwable cause) implements PrepareOrderResult {}
    }

    /**
     * Record representing the command to prepare an order for confirmation.
     */
    record PrepareOrderCommand(OrderId orderId) {}
}
