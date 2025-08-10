package jah.catchflight.query.domain;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Set;

public record LastQueriesView(Set<QueryView> queryViewItems) {
    public record QueryView(
            String queryId,
            Instant timestamp,
            String fromAirport,
            String toAirport,
            OffsetDateTime beginTime,
            OffsetDateTime endTime,
            Set<OfferView> offers) {
        public record OfferView(String offerId, String airline, Instant timestamp) {}
    }
}
