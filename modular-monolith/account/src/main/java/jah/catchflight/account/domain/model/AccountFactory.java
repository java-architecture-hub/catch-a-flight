package jah.catchflight.account.domain.model;

import jah.catchflight.account.port.out.FindCurrentAccountRepository;
import jah.catchflight.common.annotations.domain.DomainFactory;
import jah.catchflight.common.persistence.Version;
import jah.catchflight.common.policy.DomainPolicyOutput.Allowance;
import jah.catchflight.common.policy.DomainPolicyOutput.Rejection;
import jah.catchflight.sharedkernel.account.AccountType;
import jah.catchflight.sharedkernel.account.Email;
import jah.catchflight.sharedkernel.account.UserId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A factory class responsible for creating new {@link Account} instances.
 * This class is annotated as a domain factory and ensures that accounts are created
 * with valid passwords and non-existing email addresses.
 */
@Slf4j
@Component
@DomainFactory
@RequiredArgsConstructor
public class AccountFactory {
    private final FindCurrentAccountRepository findCurrentAccountRepository;
    private final List<PasswordPolicy> passwordPolicies = PasswordPolicy.passwordPolicies;

    /**
     * Creates a new {@link Account} with the specified email, password, and username.
     * Validates the password against defined password policies and checks if an account
     * with the given email already exists.
     *
     * @param email    the email address of the new account
     * @param password the password for the new account
     * @param userName the username for the new account
     * @return a new {@link Account} instance
     * @throws PasswordPolicyException      if the password does not meet the defined policies
     * @throws AccountAlreadyExistsException if an account with the given email already exists
     */
    public Account create(Email email, Password password, UserName userName) {
        verifyPasswordPolicies(password);
        verifyIfUserAlreadyExists(email);
        return buildUser(email, password, userName);
    }

    /**
     * Verifies that the provided password complies with all defined password policies.
     * Throws an exception if any policy is violated.
     */
    private void verifyPasswordPolicies(Password password) {
        var policyOutput =
                passwordPolicies.stream()
                        .map(policy -> policy.apply(password))
                        .filter(domainPolicyOutput -> domainPolicyOutput instanceof Rejection)
                        .findFirst()
                        .orElse(new Allowance());

        if (policyOutput instanceof Rejection(String reason)) {
            throw new PasswordPolicyException(reason);
        }
    }

    /**
     * Checks if an account with the given email already exists in the system.
     * Logs a debug message if no account exists, or throws an exception if an account is found.
     */
    private void verifyIfUserAlreadyExists(Email email) {
        var currentUser = findCurrentAccountRepository.findByEmail(email);
        switch (currentUser) {
            case RegularAccount(UserId userId) -> throw new AccountAlreadyExistsException(userId);
            case PremiumAccount(UserId userId) -> throw new AccountAlreadyExistsException(userId);
            case NonExistingAccount _ -> log.debug("All good. User does not exist yet.");
        }
    }

    /**
     * Builds a new {@link Account} instance with the provided email, password, and username.
     * Sets the account type to REGULAR and initializes the version to zero.
     */
    private Account buildUser(Email email, Password password, UserName userName) {
        return Account.builder()
                .email(email)
                .password(password)
                .userName(userName)
                .accountType(AccountType.REGULAR)
                .version(Version.zero())
                .build();
    }
}
