package jah.catchflight.common.events;

public interface DomainEventsPublisher {
    void publish(DomainEvent event);
}
