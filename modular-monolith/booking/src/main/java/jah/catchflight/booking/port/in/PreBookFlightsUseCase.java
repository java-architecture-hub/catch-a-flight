package jah.catchflight.booking.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;

@InboundPort
public interface PreBookFlightsUseCase {
    PreBookFlightsResult preBookFlight(PreBookFlightsCommand preBookFlightsCommand);

    public interface PreBookFlightsResult {}
    public record PreBookFlightsCommand() {}
}
