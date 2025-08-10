package jah.catchflight.engagement.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.AccountId;

import java.util.Objects;

@InboundPort
public interface DeleteUserUseCase {
    DeleteUserResult captureUserActivity(DeleteUserCommand deleteUserCommand);

    record DeleteUserCommand(AccountId accountId) {
        public DeleteUserCommand {
            Objects.requireNonNull(accountId);
        }
    }

    record DeleteUserResult() {}
}
