package jah.catchflight.query.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.UserId;

import java.util.Objects;

@InboundPort
public interface QueryFlightsUseCase {
    QueryFlightsResult query(QueryFlightsCommand command);

    record QueryFlightsCommand(UserId userId) {
        public QueryFlightsCommand {
            Objects.requireNonNull(userId);
        }
    }

    sealed interface QueryFlightsResult {
        record Success() implements QueryFlightsResult {}
        record InvalidQueryParameters(String message) implements QueryFlightsResult {}
        record InternalFailure(Throwable cause) implements QueryFlightsResult {}
    }
}
