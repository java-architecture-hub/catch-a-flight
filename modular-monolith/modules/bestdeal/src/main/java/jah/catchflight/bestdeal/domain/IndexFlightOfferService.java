package jah.catchflight.bestdeal.domain;

import jah.catchflight.bestdeal.port.in.IndexFlightOfferUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static jah.catchflight.bestdeal.port.in.IndexFlightOfferUseCase.IndexFlightOfferResult.Success;

/**
 * Service class responsible for indexing flight offers.
 * Implements the {@link IndexFlightOfferUseCase} interface to handle flight offer indexing operations.
 */
@Slf4j
@Service
public class IndexFlightOfferService implements IndexFlightOfferUseCase {
    /**
     * Indexes a flight offer based on the provided command.
     * <p>
     * This method processes the {@link IndexFlightOfferCommand} to index a flight offer,
     * logs the operation, and returns a successful result containing a unique offer index ID.
     * </p>
     *
     * @param indexFlightOfferCommand the command containing details for indexing the flight offer
     * @return an {@link IndexFlightOfferResult} containing the result of the indexing operation,
     * including a unique {@link OfferIndexId}
     */
    @Override
    public IndexFlightOfferResult indexFlightQuery(IndexFlightOfferCommand indexFlightOfferCommand) {
        log.info("Preparing best deals for all accounts.");
        return new Success(new OfferIndexId(UUID.randomUUID()));
    }

}

