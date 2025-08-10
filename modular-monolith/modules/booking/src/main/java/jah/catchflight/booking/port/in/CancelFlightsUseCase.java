package jah.catchflight.booking.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.AccountId;

import java.util.Objects;

@InboundPort
public interface CancelFlightsUseCase {
    CancelFlightsResult cancelFlight(CancelFlightsCommand cancelFlightsCommand);

    record CancelFlightsCommand(AccountId accountId) {
        public CancelFlightsCommand {
            Objects.requireNonNull(accountId);
        }
    }

    record CancelFlightsResult() {}
}
