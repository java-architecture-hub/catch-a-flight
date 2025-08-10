package jah.catchflight.order.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.AccountId;

@InboundPort
public interface CancelOrderUseCase {
    CancelOrderResult prepareOrder(CancelOrderCommand prepareOrderCommand);

    record CancelOrderCommand() {}
    public sealed interface CancelOrderResult {
        record Success(AccountId accountId) implements CancelOrderResult {}
        record InternalFailure(Throwable cause) implements CancelOrderResult {}
    }
}
