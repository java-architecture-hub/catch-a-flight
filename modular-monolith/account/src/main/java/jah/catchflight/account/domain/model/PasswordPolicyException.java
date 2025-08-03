package jah.catchflight.account.domain.model;

/**
 * An exception thrown when a password fails to meet one or more defined password policies.
 */
public class PasswordPolicyException extends RuntimeException {

  /**
   * Constructs a {@code PasswordPolicyException} with the specified error message.
   *
   * @param message the detail message explaining the policy violation
   */
  public PasswordPolicyException(String message) {
    super(message);
  }
}
