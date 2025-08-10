package jah.catchflight.account.domain;

/**
 * An exception thrown when a password fails to meet one or more defined password policies.
 */
public class PasswordPolicyException extends RuntimeException {
    public PasswordPolicyException(String message) {
        super(message);
    }
}
