package jah.catchflight.bestdeal.adapter.in;

import jah.catchflight.bestdeal.port.in.IndexFlightQueryUseCase;
import jah.catchflight.common.annotations.hexagonal.InboundAdapter;
import jah.catchflight.event.query.FlightOfferCollected;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@InboundAdapter
@RequiredArgsConstructor
class FlightOfferCollectedHandler {
    private final IndexFlightQueryUseCase indexFlightQueryUseCase;

    @EventListener
    void handleEvent(FlightOfferCollected flightOfferCollected) {
        log.info("Event consumed. Event Id: {}", flightOfferCollected.eventId());
    }
}
