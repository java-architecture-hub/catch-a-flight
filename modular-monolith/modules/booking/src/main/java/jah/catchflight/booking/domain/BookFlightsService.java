package jah.catchflight.booking.domain;

import jah.catchflight.booking.port.in.BookFlightsUseCase;
import jah.catchflight.common.annotations.domain.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * A service responsible for handling flight booking operations in the system.
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
