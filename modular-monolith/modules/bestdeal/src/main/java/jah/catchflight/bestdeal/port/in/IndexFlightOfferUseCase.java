package jah.catchflight.bestdeal.port.in;

import jah.catchflight.bestdeal.domain.OfferIndexId;
import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.query.Offer;

import java.util.UUID;

/**
 * Defines the use case for indexing flight offers.
 * <p>
 * This interface serves as an inbound port for handling flight offer indexing operations.
 * Implementations of this interface are responsible for processing flight offer data
 * and returning the result of the indexing operation.
 * </p>
 */
@InboundPort
public interface IndexFlightOfferUseCase {
    /**
     * Indexes a flight offer based on the provided command.
     *
     * @param indexFlightOfferCommand the {@link IndexFlightOfferCommand} containing the flight offer details to be indexed
     * @return an {@link IndexFlightOfferResult} representing the outcome of the indexing operation
     */
    IndexFlightOfferResult indexFlightQuery(IndexFlightOfferCommand indexFlightOfferCommand);

    /**
     * Sealed interface representing the possible results of a flight offer indexing operation.
     */
    sealed interface IndexFlightOfferResult {
        record Success(OfferIndexId indexId) implements IndexFlightOfferResult {}
        record InternalFailure(Throwable cause) implements IndexFlightOfferResult {}
    }

    /**
     * A record representing the command to index a flight offer.
     */
    record IndexFlightOfferCommand(Offer offer) {}
}
