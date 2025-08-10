package jah.catchflight.booking.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;

@InboundPort
public interface PreBookFlightsUseCase {
    PreBookFlightsResult preBookFlight(PreBookFlightsCommand preBookFlightsCommand);

    public record PreBookFlightsCommand() {}
    public interface PreBookFlightsResult {}
}
