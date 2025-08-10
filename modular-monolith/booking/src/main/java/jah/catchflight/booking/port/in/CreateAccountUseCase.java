package jah.catchflight.booking.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.AccountType;
import jah.catchflight.sharedkernel.account.UserId;

import java.util.Objects;

@InboundPort
public interface CreateAccountUseCase {
    CreateUserResult createUser(CreateUserCommand command);

    record CreateUserCommand(UserId userId, AccountType accountType) {
        public CreateUserCommand {
            Objects.requireNonNull(userId);
            Objects.requireNonNull(accountType);
        }
    }

    sealed interface CreateUserResult {
        record Success(UserId userId) implements CreateUserResult {}
        record ExistingUserFailure(String message) implements CreateUserResult {}
        record InternalFailure(Throwable cause) implements CreateUserResult {}
    }
}
