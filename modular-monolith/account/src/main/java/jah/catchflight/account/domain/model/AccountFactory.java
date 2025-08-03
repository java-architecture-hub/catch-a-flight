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

import java.util.List;

@Slf4j
@DomainFactory
@RequiredArgsConstructor
public class AccountFactory {
    private final FindCurrentAccountRepository findCurrentAccountRepository;
    private final List<PasswordPolicy> passwordPolicies = PasswordPolicy.passwordPolicies;

    public Account create(Email email, Password password, UserName userName) {
        verifyPasswordPolicies(password);
        verifyIfUserAlreadyExists(email);
        return buildUser(email, password, userName);
    }

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

    private void verifyIfUserAlreadyExists(Email email) {
        var currentUser = findCurrentAccountRepository.findByEmail(email);
        switch (currentUser) {
            case RegularAccount(UserId userId) -> throw new AccountAlreadyExistsException(userId);
            case PremiumAccount(UserId userId) -> throw new AccountAlreadyExistsException(userId);
            case NonExistingAccount _ -> log.debug("All good. User does not exist yet.");
        }
    }

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

