package jah.catchflight.booking.domain;

import jah.catchflight.booking.port.in.BookFlightsUseCase;
import jah.catchflight.common.annotations.domain.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@DomainService
@RequiredArgsConstructor
public class BookFlightsService implements BookFlightsUseCase {
    @Override
    public BookFlightsResult bookFlight(BookFlightsCommand bookFlightsCommand) {
        throw new UnsupportedOperationException();
    }
}
