package jah.catchflight.account.port.in;

import jah.catchflight.account.domain.model.Password;
import jah.catchflight.account.domain.model.UserName;
import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.Email;
import jah.catchflight.sharedkernel.account.UserId;

import java.util.Objects;

/**
 * Defines the inbound port for creating a new user account.
 * This interface specifies the contract for the account creation use case,
 * allowing clients to initiate the creation of a user account with the required details.
 */
@InboundPort
public interface CreateAccountUseCase {
    /**
     * Creates a new user account based on the provided command.
     *
     * @param command the {@link CreateAccountCommand} containing the email, password, and username for the new account
     * @return a {@link CreateAccountResult} indicating the outcome of the account creation process
     */
    CreateAccountResult createUser(CreateAccountCommand command);

    /**
     * A record representing the command to create a new user account.
     * It encapsulates the required details for account creation.
     *
     * @param email    the email address of the user
     * @param password the password for the user account
     * @param userName the username for the user account
     */
    record CreateAccountCommand(Email email, Password password, UserName userName) {
        public CreateAccountCommand {
            Objects.requireNonNull(email);
            Objects.requireNonNull(password);
            Objects.requireNonNull(userName);
        }
    }

    /**
     * A sealed interface representing the possible outcomes of the account creation process.
     * It defines the various result types for success and failure scenarios.
     */
    sealed interface CreateAccountResult {
        record Success(UserId userId) implements CreateAccountResult {}
        record ExistingAccountFailure(String message) implements CreateAccountResult {}
        record PasswordPolicyFailure(String message) implements CreateAccountResult {}
        record InternalFailure(Throwable cause) implements CreateAccountResult {}
    }
}
