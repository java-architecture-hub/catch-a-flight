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

    private static class UpgradeUserMapper {
        UpgradeUserCommand toCommand(AccountSubscriptionPaid event) {
            return new UpgradeUserCommand(new UserId(event.eventId()));
        }
    }
}
