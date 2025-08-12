package jah.catchflight.order.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.order.domain.OrderDetails;
import jah.catchflight.order.domain.OrderId;
import jah.catchflight.order.domain.UserDetails;
import jah.catchflight.sharedkernel.account.AccountId;

/**
 * Defines a use case for creating and preparing orders in the system.
 * This interface provides a contract for preparing an order based on the provided
 * command, encapsulating the business logic for order creation.
 */
@InboundPort
public interface PrepareOrderUseCase {

    /**
     * Prepares an order based on the provided command.
     *
     * @param prepareOrderCommand the command containing details required to prepare the order
     * @return a {@link ConfirmOrderResult} indicating the outcome of the order preparation
     */
    ConfirmOrderResult prepareOrder(ConfirmOrderCommand prepareOrderCommand);

    /**
     * Sealed interface representing the result of an order preparation attempt.
     */
    public sealed interface ConfirmOrderResult {
        record Success(OrderId orderId) implements ConfirmOrderResult {}
        record InternalFailure(Throwable cause) implements ConfirmOrderResult {}
    }

    /**
     * Record representing the command to prepare an order.
     */
    record ConfirmOrderCommand(
            AccountId accountId,
            UserDetails userDetails,
            OrderDetails orderDetails) {}
}
