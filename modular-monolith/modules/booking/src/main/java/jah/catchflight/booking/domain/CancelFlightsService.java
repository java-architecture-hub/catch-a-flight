package jah.catchflight.booking.domain;

import jah.catchflight.booking.port.in.CancelFlightsUseCase;
import jah.catchflight.common.annotations.domain.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@DomainService
@RequiredArgsConstructor
public class CancelFlightsService implements CancelFlightsUseCase {
    @Override
    public CancelFlightsResult cancelFlight(CancelFlightsCommand cancelFlightsCommand) {
        throw new UnsupportedOperationException();
    }
}
