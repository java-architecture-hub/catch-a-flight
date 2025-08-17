package jah.catchflight.bestdeal.domain;

import jah.catchflight.common.annotations.domain.DomainValueObject;

import java.util.UUID;

@DomainValueObject
public record OfferIndexId(UUID value) {}
