package jah.catchflight.account.domain.model;

import jah.catchflight.sharedkernel.account.AccountId;

/**
 * An exception thrown when an attempt is made to create an account for a user that already exists.
 * This exception includes the {@link AccountId} of the existing user.
 */
public class AccountAlreadyExistsException extends RuntimeException {
    private final AccountId accountId;

    /**
     * Constructs an {@code AccountAlreadyExistsException} with the specified user ID.
     *
     * @param accountId the ID of the user that already exists
     */
    public AccountAlreadyExistsException(AccountId accountId) {
        this.accountId = accountId;
    }

    /**
     * Returns the error message indicating that the user already exists, formatted with the user ID.
     */
    @Override
    public String getMessage() {
        return String.format("User already exists: %s", accountId.value());
    }
}
