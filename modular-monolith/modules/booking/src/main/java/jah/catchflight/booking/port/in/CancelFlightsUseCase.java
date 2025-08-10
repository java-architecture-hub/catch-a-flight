package jah.catchflight.booking.port.in;

import jah.catchflight.booking.domain.BookingId;
import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.AccountId;

import java.util.Objects;

/**
 * Defines a use case for canceling flights in the system.
 * This interface provides a contract for initiating a flight cancellation operation
 * based on a provided command, returning the result of the operation.
 */
@InboundPort
public interface CancelFlightsUseCase {
    /**
     * Cancels a flight based on the provided command.
     *
     * @param cancelFlightsCommand the command containing the account ID and booking ID for cancellation
     * @return a {@link CancelFlightsResult} indicating the outcome of the cancellation operation
     * @throws IllegalArgumentException if the cancelFlightsCommand is null
     */
    CancelFlightsResult cancelFlight(CancelFlightsCommand cancelFlightsCommand);

    /**
     * Represents the result of a flight cancellation operation.
     * This sealed interface defines the possible outcomes of a cancellation attempt.
     */
    sealed interface CancelFlightsResult {
        record Success() implements CancelFlightsResult {}
        record InternalFailure(Throwable cause) implements CancelFlightsResult {}
    }
    /**
     * A command to cancel a flight, containing the account ID and booking ID.
     */
    record CancelFlightsCommand(AccountId accountId, BookingId bookingId) {
        public CancelFlightsCommand {
            Objects.requireNonNull(accountId, "accountId must not be null");
            Objects.requireNonNull(bookingId, "bookingId must not be null");
        }
    }
}
