package jah.catchflight.account.port.out;

import jah.catchflight.common.annotations.hexagonal.OutboundPort;
import jah.catchflight.common.events.DomainEvent;

/**
 * Defines the outbound port for publishing account-related domain events. This interface specifies
 * the contract for sending domain events to external systems or event handlers, enabling
 * communication of significant account changes.
 */
@OutboundPort
public interface AccountEventPublisher {

	void publish(DomainEvent event);
}
