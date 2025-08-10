package jah.catchflight.engagement.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.UserId;

import java.util.Objects;

@InboundPort
public interface DeleteUserUseCase {
    DeleteUserResult captureUserActivity(DeleteUserCommand deleteUserCommand);

    record DeleteUserCommand(UserId userId) {
        public DeleteUserCommand {
            Objects.requireNonNull(userId);
        }
    }

    record DeleteUserResult() {}
}
