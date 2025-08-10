package jah.catchflight.account.domain.model;

import jah.catchflight.sharedkernel.account.UserId;

/**
 * An exception thrown when an attempt is made to create an account for a user that already exists.
 * This exception includes the {@link UserId} of the existing user.
 */
public class AccountAlreadyExistsException extends RuntimeException {
    private final UserId userId;

    /**
     * Constructs an {@code AccountAlreadyExistsException} with the specified user ID.
     *
     * @param userId the ID of the user that already exists
     */
    public AccountAlreadyExistsException(UserId userId) {
        this.userId = userId;
    }

    /**
     * Returns the error message indicating that the user already exists, formatted with the user ID.
     */
    @Override
    public String getMessage() {
        return String.format("User already exists: %s", userId.value());
    }
}
