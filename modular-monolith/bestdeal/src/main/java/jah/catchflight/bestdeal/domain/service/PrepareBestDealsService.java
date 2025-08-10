package jah.catchflight.bestdeal.domain.service;

import jah.catchflight.bestdeal.port.in.PrepareBestDealsUseCase;
import org.springframework.stereotype.Service;

@Service
class PrepareBestDealsService implements PrepareBestDealsUseCase {
    @Override
    public PrepareBestDealsResult indexBestDeals(PrepareBestDealsCommand prepareBestDealsCommand) {
        throw new UnsupportedOperationException();
    }
}
