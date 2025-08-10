package jah.catchflight.order.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.AccountId;

@InboundPort
public interface PrepareOrderUseCase {
    PrepareOrderResult prepareOrder(PrepareOrderCommand prepareOrderCommand);

    record PrepareOrderCommand() {}
    public sealed interface PrepareOrderResult {
        record Success(AccountId accountId) implements PrepareOrderResult {}
        record InternalFailure(Throwable cause) implements PrepareOrderResult {}
    }
}
