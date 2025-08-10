package jah.catchflight.account.domain;

import jah.catchflight.common.annotations.domain.DomainPolicy;
import jah.catchflight.common.policy.DomainPolicyOutput;
import jah.catchflight.common.policy.DomainPolicyOutput.Allowance;
import jah.catchflight.common.policy.DomainPolicyOutput.Rejection;

import java.util.List;
import java.util.function.Function;

/**
 * A domain policy interface for validating passwords.
 * Implementations of this interface define rules for password validation and produce a
 * {@link DomainPolicyOutput} indicating whether the password meets the policy requirements.
 */
@DomainPolicy
interface PasswordPolicy extends Function<Password, DomainPolicyOutput> {

    /**
     * A policy that checks if the password contains at least 12 characters.
     * Returns a {@link Rejection} if the password is shorter than 12 characters, otherwise an {@link Allowance}.
     */
    PasswordPolicy shouldContainsAtLeast12Characters =
            password -> {
                if (password.value().length() < 12) {
                    return new Rejection("Password should contain at least 12 characters");
                } else {
                    return new Allowance();
                }
            };

    /**
     * A policy that checks if the password contains at least one lowercase letter.
     * Returns a {@link Rejection} if no lowercase letter is found, otherwise an {@link Allowance}.
     */
    PasswordPolicy shouldContainsLowerCaseLetters =
            password -> {
                if (password.value().matches("[a-z]+")) {
                    return new Rejection("Password should contain lower case letter");
                } else {
                    return new Allowance();
                }
            };

    /**
     * A policy that checks if the password contains at least one uppercase letter.
     * Returns a {@link Rejection} if no uppercase letter is found, otherwise an {@link Allowance}.
     */
    PasswordPolicy shouldContainsUpperCaseLetters =
            password -> {
                if (password.value().matches("[A-Z]+")) {
                    return new Rejection("Password should contain upper case letter");
                } else {
                    return new Allowance();
                }
            };

    /**
     * A policy that checks if the password contains at least one digit.
     * Returns a {@link Rejection} if no digit is found, otherwise an {@link Allowance}.
     */
    PasswordPolicy shouldContainsDigits =
            password -> {
                if (password.value().matches("[0-9]+")) {
                    return new Rejection("Password should contain digit");
                } else {
                    return new Allowance();
                }
            };

    /**
     * A list of all defined password policies to be applied during password validation.
     * Includes policies for minimum length, lowercase letters, uppercase letters, and digits.
     */
    List<PasswordPolicy> passwordPolicies =
            List.of(
                    shouldContainsAtLeast12Characters,
                    shouldContainsLowerCaseLetters,
                    shouldContainsUpperCaseLetters,
                    shouldContainsDigits);
}
