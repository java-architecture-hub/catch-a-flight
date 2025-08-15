package jah.catchflight.query.domain;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.Objects;
import java.util.UUID;

/**
 * A record representing a unique identifier for a query, using a UUID.
 *
 * @param id the UUID value of the query identifier, must not be null
 */
@DomainValueObject
public record QueryId(UUID id) {
    public QueryId {
        Objects.requireNonNull(id, "Query ID must not be null");
    }
}
