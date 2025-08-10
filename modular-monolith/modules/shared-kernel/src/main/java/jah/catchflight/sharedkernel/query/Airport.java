
package jah.catchflight.sharedkernel.query;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.Objects;

@DomainValueObject
public record Airport(IATACode code) {
    public Airport {
        Objects.requireNonNull(code);
    }
}
