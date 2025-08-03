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

    /**
     * The use case responsible for executing the account upgrade logic.
     */
    private final UpgradeAccountUseCase upgradeAccountUseCase;

    /**
     * The mapper responsible for converting subscription paid events to domain commands.
     */
    private final UpgradeUserMapper upgradeUserMapper;

    /**
     * Handles the {@link AccountSubscriptionPaid} event to initiate an account upgrade.
     * This method logs the received event, maps it to a domain command, invokes the upgrade
     * use case, and logs the outcome of the operation based on the result.
     *
     * @param event the subscription paid event containing the account details
     */
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
        /**
         * Converts an {@link AccountSubscriptionPaid} event to a {@link UpgradeUserCommand}.
         * Maps the event's account ID to a {@link UserId} domain value object and constructs
         * a command for the upgrade use case.
         *
         * @param event the subscription paid event containing the account ID
         * @return a {@link UpgradeUserCommand} for the use case
         */
        UpgradeUserCommand toCommand(AccountSubscriptionPaid event) {
            return new UpgradeUserCommand(new UserId(event.eventId()));
        }
    }
}

