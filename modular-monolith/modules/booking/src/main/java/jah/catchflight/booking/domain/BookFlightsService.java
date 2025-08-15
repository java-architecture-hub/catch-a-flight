package jah.catchflight.booking.domain;

import jah.catchflight.booking.port.in.BookFlightsUseCase;
import jah.catchflight.common.annotations.domain.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * A service responsible for handling flight booking operations in the system.
 * <p>
 * This class implements the {@link BookFlightsUseCase} interface to encapsulate the
 * business logic for booking flights based on a provided command. It is annotated as a
 * Spring {@code @Service} and a domain service to indicate its role in the application
 * layer and domain logic. The service may trigger events such as {@link FlightOfferCollected}
 * or interact with user activity tracking via {@link UserActivity#FLIGHT_BOOKED}.
 * </p>
 *
 * @see BookFlightsUseCase
 * @see BookFlightsCommand
 * @see BookFlightsResult
 * @see FlightOfferCollected
 * @see UserActivity
 */
@Service
@DomainService
@RequiredArgsConstructor
public class BookFlightsService implements BookFlightsUseCase {
    /**
     * {@inheritDoc}
     */
    @Override
    public BookFlightsResult bookFlight(BookFlightsCommand bookFlightsCommand) {
        throw new UnsupportedOperationException();
    }
}
