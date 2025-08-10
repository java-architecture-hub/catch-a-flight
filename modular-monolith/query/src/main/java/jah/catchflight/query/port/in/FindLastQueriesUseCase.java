package jah.catchflight.query.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.query.domain.LastQueriesView;
import jah.catchflight.sharedkernel.account.UserId;

@InboundPort
public interface FindLastQueriesUseCase {
    LastQueriesView findLastQueries(UserId userId);
}
