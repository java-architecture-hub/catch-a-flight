package jah.catchflight.order.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.order.domain.OrderDetails;
import jah.catchflight.order.domain.OrderId;
import jah.catchflight.order.domain.UserDetails;
import jah.catchflight.sharedkernel.account.AccountId;

/**
 * Defines a use case for confirming orders in the system.
 * This interface provides a contract for preparing an order for confirmation based on the provided
 * command, encapsulating the business logic for order confirmation.
 */
@InboundPort
public interface ConfirmOrderUseCase {

    /**
     * Prepares an order for confirmation based on the provided command.
     *
     * @param prepareOrderCommand the command containing details required to prepare the order for confirmation
     * @return a {@link PrepareOrderResult} indicating the outcome of the order preparation
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
