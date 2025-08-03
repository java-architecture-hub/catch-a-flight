package jah.catchflight.common.events;

import java.util.UUID;

public interface DomainEvent {
    UUID eventId();
}
