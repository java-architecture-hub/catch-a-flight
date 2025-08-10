package jah.catchflight.order.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.UserId;

@InboundPort
public interface ConfirmOrderUseCase {
    ConfirmOrderResult prepareOrder(ConfirmOrderCommand prepareOrderCommand);

    record ConfirmOrderCommand() {}
    public sealed interface ConfirmOrderResult {
        record Success(UserId userId) implements ConfirmOrderResult {}
        record InternalFailure(Throwable cause) implements ConfirmOrderResult {}
    }
}
