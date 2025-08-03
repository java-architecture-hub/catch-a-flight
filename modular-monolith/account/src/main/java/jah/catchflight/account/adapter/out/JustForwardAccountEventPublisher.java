package jah.catchflight.account.adapter.out;

import jah.catchflight.account.port.out.AccountEventPublisher;
import jah.catchflight.common.annotations.hexagonal.OutboundAdapter;
import jah.catchflight.common.events.DomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@OutboundAdapter
@RequiredArgsConstructor
class JustForwardAccountEventPublisher implements AccountEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(DomainEvent event) {
        log.info("Event published. Event id: {}. Event body: {}", event.eventId(), event);
        applicationEventPublisher.publishEvent(event);
    }
}
