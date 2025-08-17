package jah.catchflight.booking.port.in;

import jah.catchflight.booking.domain.BookingId;
import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.AccountId;
import jah.catchflight.sharedkernel.query.OfferId;

import java.util.Objects;

/**
 * Defines a use case for booking flights in the system.
 * This interface provides a contract for initiating a flight booking operation
 * based on a provided command, returning the result of the operation.
 */
@InboundPort
public interface BookFlightsUseCase {
    /**
     * Books a flight based on the provided command.
     */
    BookFlightsResult bookFlight(BookFlightsCommand bookFlightsCommand);

    /**
     * Represents the result of a flight booking operation.
     */
    interface BookFlightsResult {
        record Success(BookingId bookingId) implements BookFlightsResult {}
        record InternalFailure(Throwable cause) implements BookFlightsResult {}
    }

    /**
     * A command to book a flight, containing the account ID and offer ID.
     */
    record BookFlightsCommand(AccountId accountId, OfferId offerId) {
        public BookFlightsCommand {
            Objects.requireNonNull(accountId, "accountId must not be null");
            Objects.requireNonNull(offerId, "offerId must not be null");
        }
    }
}
