package jah.catchflight.booking.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.AccountId;

@InboundPort
public interface RescheduleFlightsUseCase {
    RescheduleFlightsResult rescheduleFlight(RescheduleFlightsInput rescheduleFlightsInput);

    interface RescheduleFlightsResult {}
    record RescheduleFlightsInput(AccountId accountId) {}
}
