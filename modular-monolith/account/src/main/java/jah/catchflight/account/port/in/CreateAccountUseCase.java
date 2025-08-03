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
        /**
         * Constructs a new {@link CreateAccountCommand} with validation.
         * Ensures that none of the provided parameters are null.
         *
         * @param email    the email address, must not be null
         * @param password the password, must not be null
         * @param userName the username, must not be null
         * @throws NullPointerException if any parameter is null
         */
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
        /**
         * Represents a successful account creation.
         *
         * @param userId the unique identifier of the created user account
         */
        record Success(UserId userId) implements CreateAccountResult {}

        /**
         * Represents a failure due to an existing account with the same email.
         *
         * @param message the error message describing the failure
         */
        record ExistingAccountFailure(String message) implements CreateAccountResult {}

        /**
         * Represents a failure due to the password not meeting policy requirements.
         *
         * @param message the error message describing the policy violation
         */
        record PasswordPolicyFailure(String message) implements CreateAccountResult {}

        /**
         * Represents an internal failure during account creation.
         *
         * @param cause the throwable cause of the failure
         */
        record InternalFailure(Throwable cause) implements CreateAccountResult {}
    }
}
