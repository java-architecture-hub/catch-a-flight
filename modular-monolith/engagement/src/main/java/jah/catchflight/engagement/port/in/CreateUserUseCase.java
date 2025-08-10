package jah.catchflight.engagement.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.AccountId;

import java.util.Objects;

@InboundPort
public interface CreateUserUseCase {
    CreateUserResult captureUserActivity(CreateUserCommand createUserCommand);

    record CreateUserCommand(AccountId accountId) {
        public CreateUserCommand {
            Objects.requireNonNull(accountId);
        }
    }

    record CreateUserResult() {}
}
