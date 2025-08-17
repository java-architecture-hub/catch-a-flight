package jah.catchflight.bestdeal.adapter.in;

import jah.catchflight.bestdeal.port.in.PrepareBestDealsUseCase;
import jah.catchflight.common.annotations.hexagonal.InboundAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Scheduler component responsible for periodically triggering the indexing of best deals.
 * Acts as an inbound adapter to initiate the best deals indexing process at regular intervals.
 */
@Component
@InboundAdapter
@RequiredArgsConstructor
public class IndexBestDealsScheduler {
    private final PrepareBestDealsUseCase prepareBestDealsUseCase;

    /**
     * Periodically triggers the indexing of best deals.
     * <p>
     * This method is scheduled to run every 5 minutes, invoking the {@link PrepareBestDealsUseCase#indexBestDeals()}
     * method to prepare and index the best deals for all accounts.
     * </p>
     */
    @Scheduled(fixedRate = 5L, timeUnit = TimeUnit.MINUTES)
    void run() {
        prepareBestDealsUseCase.indexBestDeals();
    }
}
