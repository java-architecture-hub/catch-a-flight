package jah.catchflight.account.adapter.out;

import jah.catchflight.account.port.out.AccountEventPublisher;
import jah.catchflight.common.annotations.hexagonal.OutboundAdapter;
import jah.catchflight.common.events.DomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Publishes account-related domain events using Spring's ApplicationEventPublisher.
 * This component acts as an outbound adapter, forwarding events to the application event system.
 */
@Slf4j
@Component
@OutboundAdapter
@RequiredArgsConstructor
class JustForwardAccountEventPublisher implements AccountEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * Publishes a domain event to the application event system.
     *
     * @param event the domain event to be published
     */
    @Override
    public void publish(DomainEvent event) {
        log.info("Event published. Event id: {}. Event body: {}", event.eventId(), event);
        applicationEventPublisher.publishEvent(event);
    }
}
