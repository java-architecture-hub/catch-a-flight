package jah.catchflight.query.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.query.domain.QueryId;

import java.util.Objects;

@InboundPort
public interface CollectOffersUserCase {
    public CollectOffersResult collectOffers(CollectOffersCommand command);

    record CollectOffersCommand(QueryId queryId) {
        public CollectOffersCommand {
            Objects.requireNonNull(queryId);
        }
    }
    sealed interface CollectOffersResult {
        record Success() implements CollectOffersResult {}
        record NoOffers() implements CollectOffersResult {}
        record QueryNotFound() implements CollectOffersResult {}
        record InternalFailure(Throwable cause) implements CollectOffersResult {}
    }
}
