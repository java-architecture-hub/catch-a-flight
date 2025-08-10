package jah.catchflight.bestdeal.port.in;

import jah.catchflight.bestdeal.domain.FlightQeuryIndexId;
import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.AccountId;
import jah.catchflight.sharedkernel.query.Route;

/**
 * Defines a use case for indexing flight queries, serving as an inbound port for handling flight query indexing operations.
 * This interface provides a contract for processing flight query commands and returning the result of the indexing operation.
 */
@InboundPort
public interface IndexFlightQueryUseCase {

    /**
     * Executes a flight query indexing operation based on the provided command.
     *
     * @param indexFlightQueryCommand the command containing the account ID and route for the flight query
     * @return an {@link IndexFlightQueryResult} indicating the outcome of the operation
     */
    IndexFlightQueryResult indexFlightQuery(IndexFlightQueryCommand indexFlightQueryCommand);

    /**
     * A record representing the command to index a flight query.
     */
    record IndexFlightQueryCommand(AccountId accountId, Route route) {}

    /**
     * A sealed interface representing the result of a flight query indexing operation.
     */
    sealed interface IndexFlightQueryResult {
        record Success(FlightQeuryIndexId indexId) implements IndexFlightQueryResult {}
        record InternalFailure(Throwable cause) implements IndexFlightQueryResult {}
    }
}
