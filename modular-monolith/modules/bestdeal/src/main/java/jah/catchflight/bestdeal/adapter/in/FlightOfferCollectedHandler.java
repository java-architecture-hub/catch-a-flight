package jah.catchflight.bestdeal.adapter.in;

import jah.catchflight.bestdeal.port.in.IndexFlightOfferUseCase;
import jah.catchflight.bestdeal.port.in.IndexFlightOfferUseCase.IndexFlightOfferCommand;
import jah.catchflight.common.annotations.hexagonal.InboundAdapter;
import jah.catchflight.event.query.FlightOfferCollected;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Handler service for processing flight offer collection events.
 * Acts as an inbound adapter to consume {@link FlightOfferCollected} events and trigger
 * further processing through the {@link IndexFlightOfferUseCase}.
 */
@Slf4j
@Service
@InboundAdapter
@RequiredArgsConstructor
public class FlightOfferCollectedHandler {
    private final IndexFlightOfferUseCase indexFlightOfferUseCase;

    /**
     * Handles the {@link FlightOfferCollected} event by consuming and logging it.
     * <p>
     * This method is triggered when a {@link FlightOfferCollected} event is published.
     * It logs the event ID for tracking purposes and can be extended to initiate further
     * processing via the {@link IndexFlightOfferUseCase}.
     * </p>
     *
     * @param flightOfferCollected the {@link FlightOfferCollected} event containing details about the collected flight offer
     */
    @EventListener
    void handleEvent(FlightOfferCollected flightOfferCollected) {
        log.info("Event consumed. Event Id: {}", flightOfferCollected.eventId());
        indexFlightOfferUseCase.indexFlightQuery(
                new IndexFlightOfferCommand(flightOfferCollected.offer()));
    }
}
