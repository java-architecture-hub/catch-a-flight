package jah.catchflight.account.domain.service;

import jah.catchflight.account.domain.model.Account;
import jah.catchflight.account.domain.model.AccountFactory;
import jah.catchflight.account.domain.model.NonExistingAccount;
import jah.catchflight.account.domain.model.PasswordPolicyException;
import jah.catchflight.event.account.AccountCreated;
import jah.catchflight.event.account.AccountCreationFailed;
import jah.catchflight.account.port.in.CreateAccountUseCase;
import jah.catchflight.account.port.out.AccountEventPublisher;
import jah.catchflight.account.port.out.CreateAccountRepository;
import jah.catchflight.account.port.out.FindCurrentAccountRepository;
import jah.catchflight.common.annotations.domain.DomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static jah.catchflight.account.port.in.CreateAccountUseCase.CreateAccountResult.*;

/**
 * Domain service responsible for creating user accounts.
 * Implements {@link CreateAccountUseCase} to encapsulate the business logic for account creation.
 */
@Slf4j
@Service
@DomainService
@RequiredArgsConstructor
public class CreateAccountService implements CreateAccountUseCase {
    private static final String ACCOUNT_ALREADY_EXISTS = "Account already exists";

    private final AccountFactory accountFactory;
    private final CreateAccountRepository createAccountRepository;
    private final FindCurrentAccountRepository findCurrentAccountRepository;
    private final AccountEventPublisher accountEventPublisher;

    /**
     * Creates a new user account based on the provided command.
     *
     * @param command the {@link CreateAccountCommand} containing user account details
     * @return a {@link CreateAccountResult} indicating the outcome of the account creation
     * @throws IllegalArgumentException if the provided command is null
     */
    @Override
    @Transactional
    public CreateAccountResult createUser(CreateAccountCommand command) {
        if (command == null) {
            log.error("CreateAccountCommand is null");
            throw new IllegalArgumentException("CreateAccountCommand cannot be null");
        }

        try {
            // Create and persist account
            return processAccountCreation(command);
        } catch (PasswordPolicyException ex) {
            return handlePasswordPolicyFailure(command, ex);
        } catch (Exception ex) {
            return handleInternalFailure(command, ex);
        }
    }

    /**
     * Processes the account creation logic.
     *
     * @param command the command containing account creation details
     * @return the result of the account creation
     */
    private CreateAccountResult processAccountCreation(CreateAccountCommand command) {
        var existingAccount = findCurrentAccountRepository.findByEmail(command.email());
        if (!(existingAccount instanceof NonExistingAccount)) {
            return new ExistingAccountFailure(ACCOUNT_ALREADY_EXISTS);
        }

        var account = accountFactory.create(
                command.email(),
                command.password(),
                command.userName()
        );
        var persistedAccount = createAccountRepository.create(account);

        log.info("Account created successfully for email: {}, userId: {}",
                persistedAccount.getEmail(),
                persistedAccount.getAccountId());

        emitAccountCreated(persistedAccount);
        return new Success(persistedAccount.getAccountId());
    }

    /**
     * Publishes an {@link AccountCreated} event for a successfully created account.
     *
     * @param account the created account
     */
    private void emitAccountCreated(Account account) {
        accountEventPublisher.publish(
                new AccountCreated(
                        UUID.randomUUID(),
                        account.getAccountId(),
                        account.getUserName(),
                        account.getAccountType(),
                        account.getEmail()
                ));
    }

    /**
     * Publishes an {@link AccountCreationFailed} event when account creation fails.
     *
     * @param command the command that failed
     * @param message the failure message
     */
    private void emitAccountCreationFailed(CreateAccountCommand command, String message) {
        accountEventPublisher.publish(
                new AccountCreationFailed(
                        UUID.randomUUID(),
                        command.userName(),
                        command.email(),
                        message
                ));
    }

    /**
     * Handles unexpected errors during account creation.
     *
     * @param command the command being processed
     * @param ex      the exception that occurred
     * @return an internal failure result
     */
    private CreateAccountResult handleInternalFailure(CreateAccountCommand command, Exception ex) {
        log.error("Unexpected error during account creation for email: {}", command.email(), ex);
        emitAccountCreationFailed(command, ex.getMessage());
        return new InternalFailure(ex);
    }

    /**
     * Handles password policy violations.
     *
     * @param command the command being processed
     * @param ex      the password policy exception
     * @return a password policy failure result
     */
    private CreateAccountResult handlePasswordPolicyFailure(CreateAccountCommand command, Exception ex) {
        log.error("Password policy violation for email: {}", command.email(), ex);
        emitAccountCreationFailed(command, ex.getMessage());
        return new PasswordPolicyFailure(ex.getMessage());
    }
}
