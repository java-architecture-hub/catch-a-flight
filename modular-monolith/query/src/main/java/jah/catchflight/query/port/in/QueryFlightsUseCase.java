package jah.catchflight.query.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.AccountId;

import java.util.Objects;

@InboundPort
public interface QueryFlightsUseCase {
    QueryFlightsResult query(QueryFlightsCommand command);

    record QueryFlightsCommand(AccountId accountId) {
        public QueryFlightsCommand {
            Objects.requireNonNull(accountId);
        }
    }

    sealed interface QueryFlightsResult {
        record Success() implements QueryFlightsResult {}
        record InvalidQueryParameters(String message) implements QueryFlightsResult {}
        record InternalFailure(Throwable cause) implements QueryFlightsResult {}
    }
}
