package jah.catchflight.query.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.query.domain.QueryId;

import java.util.Objects;

/**
 * Defines the use case for collecting offers based on a given command.
 */
@InboundPort
public interface CollectOffersUserCase {
    /**
     * Collects offers based on the provided command.
     *
     * @param command the {@link CollectOffersCommand} containing the query identifier for collecting offers
     * @return a {@link CollectOffersResult} indicating the outcome of the offer collection process
     */
    CollectOffersResult collectOffers(CollectOffersCommand command);

    /**
     * Sealed interface representing the possible outcomes of the offer collection process.
     */
    sealed interface CollectOffersResult {
        record Success() implements CollectOffersResult {}
        record NoOffers() implements CollectOffersResult {}
        record QueryNotFound() implements CollectOffersResult {}
        record InternalFailure(Throwable cause) implements CollectOffersResult {}
    }

    /**
     * A record representing the command to collect offers for a specific query.
     */
    record CollectOffersCommand(QueryId queryId) {
        public CollectOffersCommand {
            Objects.requireNonNull(queryId);
        }
    }
}
