package jah.catchflight.bestdeal.adapter.in;

import jah.catchflight.bestdeal.port.in.PrepareBestDealsUseCase;
import jah.catchflight.common.annotations.hexagonal.InboundAdapter;
import lombok.RequiredArgsConstructor;

@InboundAdapter
@RequiredArgsConstructor
class IndexBestDealsScheduler {
    private final PrepareBestDealsUseCase prepareBestDealsUseCase;
}
