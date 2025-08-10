package jah.catchflight.sharedkernel.query;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.UUID;

@DomainValueObject
public record OfferId(UUID value) {}
