package jah.catchflight.account.domain.model;

import jah.catchflight.sharedkernel.account.UserId;

/**
 * An exception thrown when an attempt is made to upgrade an account that is already upgraded in the CatchFlight application.
 * This runtime exception includes the {@link UserId} of the account that triggered the exception and provides a detailed
 * message indicating the reason for the failure.
 */
public class AccountAlreadyUpgradedException extends RuntimeException {

    /**
     * The unique identifier of the account that is already upgraded.
     */
    private final UserId userId;

    /**
     * Constructs an {@code AccountAlreadyUpgradedException} with the specified user ID and message.
     *
     * @param userId  the {@link UserId} of the account that is already upgraded
     * @param message the detail message explaining the reason for the exception
     */
    public AccountAlreadyUpgradedException(UserId userId, String message) {
        super(message);
        this.userId = userId;
    }

    /**
     * Returns the detail message of the exception, including the {@link UserId} value.
     * The message is formatted by appending the user ID to the original message.
     *
     * @return the formatted detail message
     */
    @Override
    public String getMessage() {
        return super.getMessage() + "[" + userId.value() + "]";
    }
}
