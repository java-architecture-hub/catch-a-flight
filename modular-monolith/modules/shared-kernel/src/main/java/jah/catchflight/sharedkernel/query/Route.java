package jah.catchflight.sharedkernel.query;

import jah.catchflight.common.annotations.domain.DomainValueObject;

@DomainValueObject
public record Route(
        Airport from,
        Airport to,
        DateTimeRange time,
        DateTimeRange returnTime) {}
