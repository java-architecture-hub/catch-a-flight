package jah.catchflight.engagement.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.UserId;

import java.util.Objects;

@InboundPort
public interface CreateUserUseCase {
    CreateUserResult captureUserActivity(CreateUserCommand createUserCommand);

    record CreateUserCommand(UserId userId) {
        public CreateUserCommand {
            Objects.requireNonNull(userId);
        }
    }

    record CreateUserResult() {}
}
