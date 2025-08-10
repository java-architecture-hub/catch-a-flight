package jah.catchflight.booking.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.UserId;

import java.util.Objects;

@InboundPort
public interface CancelFlightsUseCase {
    CancelFlightsResult cancelFlight(CancelFlightsCommand cancelFlightsCommand);

    record CancelFlightsCommand(UserId userId) {
        public CancelFlightsCommand {
            Objects.requireNonNull(userId);
        }
    }

    record CancelFlightsResult() {}
}
