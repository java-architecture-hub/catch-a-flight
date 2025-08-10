package jah.catchflight.booking.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.UserId;

@InboundPort
public interface RescheduleFlightsUseCase {
    RescheduleFlightsResult rescheduleFlight(RescheduleFlightsInput rescheduleFlightsInput);

    record RescheduleFlightsInput(UserId userId) {}
    interface RescheduleFlightsResult {}
}
