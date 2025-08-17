package jah.catchflight.bestdeal.domain;

import jah.catchflight.bestdeal.port.in.PrepareBestDealsUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static jah.catchflight.bestdeal.port.in.PrepareBestDealsUseCase.PrepareBestDealsResult.Success;

/**
 * Service class responsible for preparing the best deals for indexing.
 * Implements the {@link PrepareBestDealsUseCase} interface to provide
 * the functionality for indexing best deals across all accounts.
 */
@Slf4j
@Service
public class PrepareBestDealsService implements PrepareBestDealsUseCase {

    /**
     * Indexes the best deals for all accounts.
     * <p>
     * This method initiates the process of preparing and indexing the best deals.
     * It logs the start of the process and returns a successful result upon completion.
     * </p>
     *
     * @return a {@link PrepareBestDealsResult} indicating the success of the operation
     */
    @Override
    public PrepareBestDealsResult indexBestDeals() {
        log.info("Preparing best deals for all accounts.");
        return new Success();
    }
}
