package jah.catchflight.order.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.UserId;

@InboundPort
public interface CancelOrderUseCase {
    CancelOrderResult prepareOrder(CancelOrderCommand prepareOrderCommand);

    record CancelOrderCommand() {}
    public sealed interface CancelOrderResult {
        record Success(UserId userId) implements CancelOrderResult {}
        record InternalFailure(Throwable cause) implements CancelOrderResult {}
    }
}
