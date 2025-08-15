package jah.catchflight.order.domain;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.Objects;
import java.util.UUID;

/**
 * A record representing a unique identifier for an order, using a UUID.
 *
 * @param id the UUID value of the order identifier, must not be null
 */
@DomainValueObject
public record OrderId(UUID id) {
    public OrderId {
        Objects.requireNonNull(id, "Order ID must not be null");
    }
}
