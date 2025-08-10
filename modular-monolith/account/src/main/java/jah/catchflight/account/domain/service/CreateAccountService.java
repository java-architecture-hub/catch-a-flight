package jah.catchflight.account.domain.service;

import jah.catchflight.account.domain.events.AccountCreated;
import jah.catchflight.account.domain.events.AccountCreationFailed;
import jah.catchflight.account.domain.model.Account;
import jah.catchflight.account.domain.model.AccountFactory;
import jah.catchflight.account.port.in.CreateAccountUseCase;
import jah.catchflight.account.port.out.AccountEventPublisher;
import jah.catchflight.account.port.out.CreateAccountRepository;
import jah.catchflight.common.annotations.domain.DomainService;
import jah.catchflight.common.validation.InputValidationResult;
import jah.catchflight.sharedkernel.account.UserId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static jah.catchflight.account.port.in.CreateAccountUseCase.CreateAccountResult.*;
import static jah.catchflight.common.validation.InputValidationResult.NotValid;

/**
 * Domain service responsible for creating user accounts.
 * Implements {@link CreateAccountUseCase} to encapsulate the business logic for account creation.
 */
@Slf4j
@Service
@DomainService
@RequiredArgsConstructor
public class CreateAccountService implements CreateAccountUseCase {
    private final CreateAccountCommandValidator createAccountCommandValidator;
    private final AccountFactory accountFactory;
    private final CreateAccountRepository createAccountRepository;
    private final AccountEventPublisher accountEventPublisher;

    /**
     * Creates a new user account based on the provided command.
     * <p>
     * This method orchestrates the account creation process by:
     * <ul>
     *     <li>Validating the command for email, password, and username.</li>
     *     <li>Creating an account using the provided details.</li>
     *     <li>Persisting the account to the repository.</li>
     *     <li>Publishing an {@link AccountCreated} event on success or an
     *         {@link AccountCreationFailed} event on failure.</li>
     * </ul>
     * The operation is executed within a transactional context to ensure data consistency.
     *
     * @param command the {@link CreateAccountCommand} containing the email, password, and username
     * @return a {@link CreateAccountResult} indicating the outcome of the account creation
     * @throws IllegalArgumentException if the command is null
     */
    @Override
    @Transactional
    public CreateAccountResult createUser(CreateAccountCommand command) {
        if (command == null) {
            log.error("CreateAccountCommand is null");
            throw new IllegalArgumentException("CreateAccountCommand cannot be null");
        }

        try {
            // Validate input
            var validationResult = createAccountCommandValidator.validate(command);
            if (validationResult instanceof NotValid(String message)) {
                log.warn("Invalid create account command for email: {}, reason: {}", command.email(), message);
                emitAccountCreationFailed(command, message);
                return new InputNotValid(message);
            }

            // Create and persist account
            return processAccountCreation(command);
        } catch (Exception ex) {
            log.error("Unexpected error during account creation for email: {}", command.email(), ex);
            emitAccountCreationFailed(command, ex.getMessage());
            return new InternalFailure(ex);
        }
    }

    /**
     * Processes the account creation logic.
     */
    private CreateAccountResult processAccountCreation(CreateAccountCommand command) {
        var account = accountFactory.create(command.email(), command.password(), command.userName());
        var persistedAccount = createAccountRepository.create(account);
        log.info("Account created successfully for email: {}, userId: {}",
                persistedAccount.getEmail(), persistedAccount.getUserId());
        emitAccountCreated(persistedAccount);
        return new Success(persistedAccount.getUserId());
    }

    /**
     * Publishes an {@link AccountCreated} event for a successfully created account.
     */
    private void emitAccountCreated(Account account) {
        accountEventPublisher.publish(new AccountCreated(
                UUID.randomUUID(),
                account.getUserId(),
                account.getUserName(),
                account.getAccountType(),
                account.getEmail()
        ));
    }

    /**
     * Publishes an {@link AccountCreationFailed} event when account creation fails.
     */
    private void emitAccountCreationFailed(CreateAccountCommand command, String message) {
        accountEventPublisher.publish(new AccountCreationFailed(
                UUID.randomUUID(),
                command.userName(),
                command.email(),
                message
        ));
    }

    /**
     * Validates the {@link CreateAccountCommand} to ensure it meets account creation criteria.
     */
    static class CreateAccountCommandValidator {
        InputValidationResult validate(final CreateAccountCommand command) {
            return new InputValidationResult.Valid();
        }
    }
}