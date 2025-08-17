package jah.catchflight.booking.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.AccountId;

import java.util.Objects;

/**
 * Defines a use case for deleting user accounts in the system.
 * This interface provides a contract for initiating an account deletion operation
 * based on a provided command, returning the result of the operation.
 */
@InboundPort
public interface DeleteAccountUseCase {
    /**
     * Deletes a user account based on the provided command.
     *
     * @param deleteUserInput the command containing the account ID for deletion
     * @return a {@link DeleteAccountResult} indicating the outcome of the account deletion operation
     * @throws IllegalArgumentException if the deleteUserInput is null
     */
    DeleteAccountResult deleteAccount(DeleteAccountCommand deleteUserInput);

    /**
     * Represents the result of an account deletion operation.
     * This interface defines the possible outcomes of an account deletion attempt.
     */
     interface DeleteAccountResult {
        record Success(AccountId accountId) implements DeleteAccountResult {}
        record NonExistingAccountFailure(String message) implements DeleteAccountResult {}
        record InternalFailure(Throwable cause) implements DeleteAccountResult {}
    }

    /**
     * A command to delete a user account, containing the account ID.
     */
     record DeleteAccountCommand(AccountId accountId) {
        public DeleteAccountCommand {
            Objects.requireNonNull(accountId, "accountId must not be null");
        }
    }
}
