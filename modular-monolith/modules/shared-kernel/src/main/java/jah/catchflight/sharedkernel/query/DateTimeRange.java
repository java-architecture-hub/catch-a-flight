package jah.catchflight.sharedkernel.query;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.time.OffsetDateTime;

@DomainValueObject
public record DateTimeRange(
        OffsetDateTime begin,
        OffsetDateTime end) {}
