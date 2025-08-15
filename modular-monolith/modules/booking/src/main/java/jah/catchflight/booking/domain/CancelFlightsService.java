package jah.catchflight.booking.domain;

import jah.catchflight.booking.port.in.CancelFlightsUseCase;
import jah.catchflight.common.annotations.domain.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * A service responsible for handling flight cancellation operations in the system.
 * <p>
 * This class implements the {@link CancelFlightsUseCase} interface to encapsulate the
 * business logic for canceling flights based on a provided command. It is annotated as a
 * Spring {@code @Service} and a domain service to indicate its role in the application
 * layer and domain logic. The service may trigger events such as {@link FlightOfferCollected}
 * or interact with user activity tracking via {@link UserActivity#FLIGHT_CANCELED}.
 * </p>
 *
 * @see CancelFlightsUseCase
 * @see CancelFlightsCommand
 * @see CancelFlightsResult
 * @see BookingId
 * @see UserActivity
 */
@Service
@DomainService
@RequiredArgsConstructor
public class CancelFlightsService implements CancelFlightsUseCase {
    /**
     * {@inheritDoc}
     */
    @Override
    public CancelFlightsResult cancelFlight(CancelFlightsCommand cancelFlightsCommand) {
        throw new UnsupportedOperationException();
    }
}
