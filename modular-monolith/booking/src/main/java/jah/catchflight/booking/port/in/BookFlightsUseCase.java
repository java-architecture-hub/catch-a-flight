package jah.catchflight.booking.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.UserId;

import java.util.Objects;

@InboundPort
public interface BookFlightsUseCase {
    BookFlightsResult bookFlight(BookFlightsCommand bookFlightsCommand);

    record BookFlightsCommand(UserId userId) {
        public BookFlightsCommand {
            Objects.requireNonNull(userId);
        }
    }

    interface BookFlightsResult {}
}
