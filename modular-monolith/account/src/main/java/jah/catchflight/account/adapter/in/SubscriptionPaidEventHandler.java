package jah.catchflight.account.adapter.in;

import jah.catchflight.account.domain.events.AccountSubscriptionPaid;
import jah.catchflight.account.port.in.UpgradeAccountUseCase;
import jah.catchflight.common.annotations.hexagonal.InboundAdapter;
import jah.catchflight.sharedkernel.account.UserId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import static jah.catchflight.account.port.in.UpgradeAccountUseCase.UpgradeUserCommand;
import static jah.catchflight.account.port.in.UpgradeAccountUseCase.UpgradeUserResult.*;

/**
 * Event handler for processing {@link AccountSubscriptionPaid} events in the CatchFlight application.
 * This service acts as an inbound adapter in the hexagonal architecture, listening for subscription
 * payment events and triggering account upgrades by delegating to the {@link UpgradeAccountUseCase}.
 * It maps events to domain commands and logs the results of the upgrade operation.
 */
@Slf4j
@Service
@InboundAdapter
@RequiredArgsConstructor
class SubscriptionPaidEventHandler {
    private final UpgradeAccountUseCase upgradeAccountUseCase;
    private final UpgradeUserMapper upgradeUserMapper;

    @EventListener
    void handle(AccountSubscriptionPaid event) {
        log.info("Event received: {}", event);
        var command = upgradeUserMapper.toCommand(event);
        var result = upgradeAccountUseCase.upgradeUser(command);
        switch (result) {
            case Success() -> log.info("Account upgraded.");
            case UserAlreadyUpgradedFailure(String message) -> log.info("Account upgraded failed: {}", message);
            case UserNotFoundFailure(String message) -> log.info("Account upgraded failed: {}", message);
            case InternalFailure(Throwable cause) -> log.error("Internal failure:", cause);
        }
    }

    /**
     * Maps subscription paid events to domain commands for account upgrade operations.
     * This class is responsible for transforming an {@link AccountSubscriptionPaid} event
     * into a {@link UpgradeUserCommand} for use by the upgrade use case.
     */
    private static class UpgradeUserMapper {
        UpgradeUserCommand toCommand(AccountSubscriptionPaid event) {
            return new UpgradeUserCommand(new UserId(event.eventId()));
        }
    }
}
