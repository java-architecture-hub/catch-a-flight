package jah.catchflight.account.port.in;

import jah.catchflight.account.domain.model.Password;
import jah.catchflight.account.domain.model.UserName;
import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.Email;
import jah.catchflight.sharedkernel.account.UserId;

import java.util.Objects;

@InboundPort
public interface CreateAccountUseCase {
    CreateAccountResult createUser(CreateAccountCommand command);

    record CreateAccountCommand(Email email, Password password, UserName userName) {
        public CreateAccountCommand {
            Objects.requireNonNull(email);
            Objects.requireNonNull(password);
            Objects.requireNonNull(userName);
        }
    }

    sealed interface CreateAccountResult {
        record Success(UserId userId) implements CreateAccountResult {}
        record ExistingAccountFailure(String message) implements CreateAccountResult {}
        record PasswordPolicyFailure(String message) implements CreateAccountResult {}
        record InternalFailure(Throwable cause) implements CreateAccountResult {}
    }
}
