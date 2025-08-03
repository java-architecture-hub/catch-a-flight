package jah.catchflight.account.domain.model;

import jah.catchflight.common.annotations.domain.DomainPolicy;
import jah.catchflight.common.policy.DomainPolicyOutput;
import jah.catchflight.common.policy.DomainPolicyOutput.Allowance;
import jah.catchflight.common.policy.DomainPolicyOutput.Rejection;

import java.util.List;
import java.util.function.Function;

@DomainPolicy
interface PasswordPolicy extends Function<Password, DomainPolicyOutput> {
    PasswordPolicy shouldContainsAtLeast12Characters =
            password -> {
                if (password.value().length() < 12) {
                    return new Rejection("Password should contain at least 12 characters");
                } else {
                    return new Allowance();
                }
            };

    PasswordPolicy shouldContainsLowerCaseLetters =
            password -> {
                if (password.value().matches("[a-z]+")) {
                    return new Rejection("Password should contain lower case letter");
                } else {
                    return new Allowance();
                }
            };

    PasswordPolicy shouldContainsUpperCaseLetters =
            password -> {
                if (password.value().matches("[A-Z]+")) {
                    return new Rejection("Password should contain upper case letter");
                } else {
                    return new Allowance();
                }
            };

    PasswordPolicy shouldContainsDigits =
            password -> {
                if (password.value().matches("[0-9]+")) {
                    return new Rejection("Password should contain digit");
                } else {
                    return new Allowance();
                }
            };

    List<PasswordPolicy> passwordPolicies =
            List.of(
                    shouldContainsAtLeast12Characters,
                    shouldContainsLowerCaseLetters,
                    shouldContainsUpperCaseLetters,
                    shouldContainsDigits);
}

