package jah.catchflight.account.port.out;

import jah.catchflight.common.annotations.hexagonal.OutboundPort;
import jah.catchflight.common.events.DomainEvent;

@OutboundPort
public interface AccountEventPublisher {
    void publish(DomainEvent event);
}
