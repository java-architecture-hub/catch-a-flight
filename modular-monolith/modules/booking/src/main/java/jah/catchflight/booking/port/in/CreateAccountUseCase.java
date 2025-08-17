package jah.catchflight.booking.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.AccountId;
import jah.catchflight.sharedkernel.account.AccountType;

import java.util.Objects;

/**
 * Defines a use case for creating user accounts in the system.
 * This interface provides a contract for initiating an account creation operation
 * based on a provided command, returning the result of the operation.
 */
@InboundPort
public interface CreateAccountUseCase {
    /**
     * Creates a user account based on the provided command.
     */
    CreateAccountResult createUser(CreateAccountCommand command);

    /**
     * Represents the result of an account creation operation.
     * This sealed interface defines the possible outcomes of an account creation attempt.
     */
    sealed interface CreateAccountResult {
        record Success(AccountId accountId) implements CreateAccountResult {}
        record ExistingAccountFailure(String message) implements CreateAccountResult {}
        record InternalFailure(Throwable cause) implements CreateAccountResult {}
    }

    /**
     * A command to create a user account, containing the account ID and account type.
     */
    record CreateAccountCommand(AccountId accountId, AccountType accountType) {
        public CreateAccountCommand {
            Objects.requireNonNull(accountId, "accountId must not be null");
            Objects.requireNonNull(accountType, "accountType must not be null");
        }
    }
}
