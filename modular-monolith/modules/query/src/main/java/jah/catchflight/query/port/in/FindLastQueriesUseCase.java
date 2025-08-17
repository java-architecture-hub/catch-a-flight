package jah.catchflight.query.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.query.domain.LastQueriesView;
import jah.catchflight.sharedkernel.account.AccountId;

/**
 * Defines the use case for retrieving the last queries associated with an account.
 */
@InboundPort
public interface FindLastQueriesUseCase {
    /**
     * Retrieves the last queries for a specified account.
     */
    LastQueriesView findLastQueries(AccountId accountId);
}
