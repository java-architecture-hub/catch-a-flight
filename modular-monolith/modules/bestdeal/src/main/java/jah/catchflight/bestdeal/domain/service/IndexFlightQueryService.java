package jah.catchflight.bestdeal.domain.service;

import jah.catchflight.bestdeal.port.in.IndexFlightQueryUseCase;
import org.springframework.stereotype.Service;

@Service
class IndexFlightQueryService implements IndexFlightQueryUseCase {
    @Override
    public IndexFlightQueryResult indexFlightQuery(IndexFlightQueryCommand indexFlightQueryCommand) {
        throw new UnsupportedOperationException();
    }
}
