package jah.catchflight.query.domain;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * A record representing a collection of recent query views.
 *
 * @param queryViewItems the set of query views, must not be null
 */
@DomainValueObject
public record LastQueriesView(Set<QueryView> queryViewItems) {
    public LastQueriesView {
        Objects.requireNonNull(queryViewItems, "Query view items must not be null");
    }

    /**
     * A nested record representing a single query view with details about the query and its associated offers.
     *
     * @param queryId     the unique identifier of the query, must not be null
     * @param timestamp   the time the query was made, must not be null
     * @param fromAirport the IATA code of the departure airport, must not be null
     * @param toAirport   the IATA code of the destination airport, must not be null
     * @param beginTime   the start of the travel time range, must not be null
     * @param endTime     the end of the travel time range, must not be null
     * @param offers      the set of offers associated with the query, must not be null
     */
    public record QueryView(
            String queryId,
            Instant timestamp,
            String fromAirport,
            String toAirport,
            OffsetDateTime beginTime,
            OffsetDateTime endTime,
            Set<OfferView> offers) {
        public QueryView {
            Objects.requireNonNull(queryId, "Query ID must not be null");
            Objects.requireNonNull(timestamp, "Timestamp must not be null");
            Objects.requireNonNull(fromAirport, "Departure airport must not be null");
            Objects.requireNonNull(toAirport, "Destination airport must not be null");
            Objects.requireNonNull(beginTime, "Begin time must not be null");
            Objects.requireNonNull(endTime, "End time must not be null");
            Objects.requireNonNull(offers, "Offers must not be null");
        }

        /**
         * A nested record representing a view of an offer associated with a query.
         *
         * @param offerId   the unique identifier of the offer, must not be null
         * @param airline   the name of the airline, must not be null
         * @param timestamp the time the offer was created or updated, must not be null
         */
        public record OfferView(String offerId, String airline, Instant timestamp) {
            public OfferView {
                Objects.requireNonNull(offerId, "Offer ID must not be null");
                Objects.requireNonNull(airline, "Airline must not be null");
                Objects.requireNonNull(timestamp, "Timestamp must not be null");
            }
        }
    }
}
