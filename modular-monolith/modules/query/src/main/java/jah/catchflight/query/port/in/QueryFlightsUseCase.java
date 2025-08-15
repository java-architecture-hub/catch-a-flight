package jah.catchflight.query.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.query.domain.Offer;
import jah.catchflight.sharedkernel.account.AccountId;
import jah.catchflight.sharedkernel.query.Route;

import java.util.List;
import java.util.Objects;

/**
 * Defines the use case for querying flights based on a given command.
 */
@InboundPort
public interface QueryFlightsUseCase {
    /**
     * Queries flights based on the provided command.
     *
     * @param command the {@link QueryFlightsCommand} containing the account identifier and route details for the flight query
     * @return a {@link QueryFlightsResult} indicating the outcome of the flight query process
     */
    QueryFlightsResult query(QueryFlightsCommand command);

    /**
     * Sealed interface representing the possible outcomes of the flight query process.
     */
    sealed interface QueryFlightsResult {
        record Success(List<Offer> offers) implements QueryFlightsResult {}
        record InvalidQueryParameters(String message) implements QueryFlightsResult {}
        record InternalFailure(Throwable cause) implements QueryFlightsResult {}
    }

    /**
     * A record representing the command to query flights for a specific account and route.
     *
     * @param accountId the unique identifier of the account initiating the query
     * @param route     the {@link Route} specifying the flight query details
     */
    record QueryFlightsCommand(AccountId accountId, Route route) {
        public QueryFlightsCommand {
            Objects.requireNonNull(accountId);
            Objects.requireNonNull(route);
        }
    }
}
