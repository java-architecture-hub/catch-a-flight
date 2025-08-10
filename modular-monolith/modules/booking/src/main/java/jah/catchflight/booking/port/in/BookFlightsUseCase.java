package jah.catchflight.booking.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.AccountId;

import java.util.Objects;

@InboundPort
public interface BookFlightsUseCase {
    BookFlightsResult bookFlight(BookFlightsCommand bookFlightsCommand);

    interface BookFlightsResult {}
    record BookFlightsCommand(AccountId accountId) {
        public BookFlightsCommand {
            Objects.requireNonNull(accountId);
        }
    }
}
