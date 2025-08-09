package jah.catchflight.account.domain.service;

import jah.catchflight.account.domain.events.AccountCreated;
import jah.catchflight.account.domain.events.AccountCreationFailed;
import jah.catchflight.account.domain.model.Account;
import jah.catchflight.account.domain.model.AccountAlreadyExistsException;
import jah.catchflight.account.domain.model.AccountFactory;
import jah.catchflight.account.domain.model.PasswordPolicyException;
import jah.catchflight.account.port.in.CreateAccountUseCase;
import jah.catchflight.account.port.out.AccountEventPublisher;
import jah.catchflight.account.port.out.CreateAccountRepository;
import jah.catchflight.common.annotations.domain.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import static jah.catchflight.account.port.in.CreateAccountUseCase.CreateAccountResult.*;

import java.util.UUID;

/**
 * A domain service responsible for orchestrating the creation of new user accounts.
 * This service implements the {@link CreateAccountUseCase} interface, providing
 * business logic for account creation, including validation, persistence, and
 * event publishing. It leverages a factory for account creation, a repository for
 * persistence, and an event publisher for notifying other components of account
 * creation outcomes.
 */
@DomainService
@RequiredArgsConstructor
public class CreateAccountService implements CreateAccountUseCase {

    private final AccountFactory accountFactory;
    private final CreateAccountRepository createAccountRepository;
    private final AccountEventPublisher accountEventPublisher;

    /**
     * Creates a new user account based on the provided command.
     * <p>
     * This method orchestrates the account creation process by:
     * <ul>
     *     <li>Validating and creating an account using the provided email, password, and username.</li>
     *     <li>Persisting the account to the repository.</li>
     *     <li>Publishing an {@link AccountCreated} event upon success or an
     *         {@link AccountCreationFailed} event upon failure.</li>
     * </ul>
     * The operation is executed within a transactional context to ensure data consistency.
     *
     * @param command the {@link CreateAccountCommand} containing the email, password, and username
     *                for the new account
     * @return a {@link CreateAccountResult} indicating the outcome of the account creation process,
     * such as success or specific failure reasons
     * @throws AccountAlreadyExistsException if an account with the provided email already exists
     * @throws PasswordPolicyException       if the provided password does not meet the defined policy requirements
     * @throws RuntimeException              if an unexpected error occurs during the account creation process
     */
    @Override
    @Transactional
    public CreateAccountResult createUser(CreateAccountCommand command) {
        try {
            var user = accountFactory.create(
                    command.email(),
                    command.password(),
                    command.userName());
            var persistedUser = createAccountRepository.create(user);
            emitAccountCreated(persistedUser);
            return new Success(persistedUser.getUserId());
        } catch (AccountAlreadyExistsException ex) {
            emitAccountCreationFailed(command, ex.getMessage());
            return new ExistingAccountFailure(ex.getMessage());
        } catch (PasswordPolicyException ex) {
            emitAccountCreationFailed(command, ex.getMessage());
            return new PasswordPolicyFailure(ex.getMessage());
        } catch (Exception ex) {
            emitAccountCreationFailed(command, ex.getMessage());
            return new InternalFailure(ex);
        }
    }

    /**
     * Publishes an {@link AccountCreated} event for a successfully created account.
     *
     * @param account the {@link Account} that was successfully created
     */
    private void emitAccountCreated(Account account) {
        accountEventPublisher.publish(accountCreatedEvent(account));
    }

    /**
     * Creates an {@link AccountCreated} event with details of the newly created account.
     *
     * @param account the {@link Account} from which to extract event details
     * @return an {@link AccountCreated} event containing the account's details
     */
    private AccountCreated accountCreatedEvent(Account account) {
        return new AccountCreated(UUID.randomUUID(), account.getUserId(), account.getUserName(), account.getAccountType(), account.getEmail());
    }

    /**
     * Publishes an {@link AccountCreationFailed} event when account creation fails.
     *
     * @param command the {@link CreateAccountCommand} that triggered the failure
     * @param message the error message describing the reason for the failure
     */
    private void emitAccountCreationFailed(CreateAccountCommand command, String message) {
        accountEventPublisher.publish(accountCreationFailedEvent(command, message));
    }

    /**
     * Creates an {@link AccountCreationFailed} event with details of the failed account creation attempt.
     *
     * @param command the {@link CreateAccountCommand} that triggered the failure
     * @param message the error message describing the reason for the failure
     * @return an {@link AccountCreationFailed} event containing the failure details
     */
    private AccountCreationFailed accountCreationFailedEvent(CreateAccountCommand command, String message) {
        return new AccountCreationFailed(UUID.randomUUID(), command.userName(), command.email(), message);
    }
}