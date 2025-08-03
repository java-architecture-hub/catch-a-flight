package jah.catchflight.account.domain.model;

import jah.catchflight.sharedkernel.account.UserId;

public class AccountAlreadyExistsException extends RuntimeException {
    private static final String MESSAGE = "User already exists: %s";
    private final UserId userId;

    public AccountAlreadyExistsException(UserId userId) {
        this.userId = userId;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE, userId.value());
    }
}
