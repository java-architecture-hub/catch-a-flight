package jah.catchflight.account.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.UserId;

import java.util.Objects;

@InboundPort
public interface UpgradeAccountUseCase {
    UpgradeUserResult upgradeUser(UpgradeUserCommand command);

    record UpgradeUserCommand(UserId userId) {
        public UpgradeUserCommand {
            Objects.requireNonNull(userId);
        }
    }

    sealed interface UpgradeUserResult {
        record Success() implements UpgradeUserResult {}
        record UserNotFoundFailure(String message) implements UpgradeUserResult {}
        record UserAlreadyUpgradedFailure(String message) implements UpgradeUserResult {}
        record InternalFailure(Throwable cause) implements UpgradeUserResult {}
    }
}
