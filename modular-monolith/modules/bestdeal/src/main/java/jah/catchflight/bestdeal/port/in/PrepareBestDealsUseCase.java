package jah.catchflight.bestdeal.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.AccountId;

/**
 * Defines a use case for preparing the best deals, serving as an inbound port for handling best deals preparation operations.
 * This interface provides a contract for processing best deals commands and returning the result of the operation.
 */
@InboundPort
public interface PrepareBestDealsUseCase {

    /**
     * Executes a best deals preparation operation based on the provided command.
     *
     * @param prepareBestDealsCommand the command containing the account ID for preparing the best deals
     * @return a {@link PrepareBestDealsResult} indicating the outcome of the operation
     */
    PrepareBestDealsResult indexBestDeals(PrepareBestDealsCommand prepareBestDealsCommand);

    /**
     * An interface representing the result of a best deals preparation operation.
     */
    interface PrepareBestDealsResult {
        record Success() implements PrepareBestDealsResult {}
        record InternalFailure(Throwable cause) implements PrepareBestDealsResult {}
    }
    /**
     * A record representing the command to prepare the best deals.
     */
    record PrepareBestDealsCommand(AccountId accountId) {}
}
