package jah.catchflight.booking.domain;

import jah.catchflight.booking.port.in.CancelFlightsUseCase;
import jah.catchflight.common.annotations.domain.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * A service responsible for handling flight cancellation operations in the system.
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
