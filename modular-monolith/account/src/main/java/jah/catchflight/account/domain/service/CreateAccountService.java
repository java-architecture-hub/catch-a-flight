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

import java.util.UUID;

import static jah.catchflight.account.port.in.CreateAccountUseCase.CreateAccountResult.*;

/**
 * Service responsible for handling the creation of new user accounts.
 * This class implements the {@link CreateAccountUseCase} interface to provide
 * the business logic for account creation, utilizing a factory for account creation,
 * a repository for persistence, and an event publisher for notifications.
 */
@DomainService
@RequiredArgsConstructor
public class CreateAccountService implements CreateAccountUseCase {
    private final AccountFactory accountFactory;
    private final CreateAccountRepository createAccountRepository;
    private final AccountEventPublisher accountEventPublisher;

    /**
     * Creates a new user account based on the provided command.
     * This method orchestrates the account creation process, including validation,
     * persistence, and event publishing, within a transactional context.
     *
     * @param command the {@link CreateAccountCommand} containing the email, password, and username for the new account
     * @return a {@link CreateAccountResult} indicating the outcome of the account creation process
     * @throws AccountAlreadyExistsException if an account with the provided email already exists
     * @throws PasswordPolicyException       if the provided password does not meet policy requirements
     * @throws RuntimeException              if an unexpected error occurs during account creation
     */
    @Override
    @Transactional
    public CreateAccountResult createUser(CreateAccountCommand command) {
        try {

            var user = accountFactory.create(command.email(), command.password(), command.userName());
            var persistedUser = createAccountRepository.create(user);
            emitAccountCreated(persistedUser);
            return new CreateAccountResult.Success(persistedUser.getUserId());

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

    private void emitAccountCreated(Account account) {
        accountEventPublisher.publish(accountCreatedEvent(account));
    }

    private AccountCreated accountCreatedEvent(Account account) {
        return new AccountCreated(UUID.randomUUID(), account.getUserId(), account.getUserName(), account.getAccountType(), account.getEmail());
    }

    private void emitAccountCreationFailed(CreateAccountCommand command, String message) {
        accountEventPublisher.publish(accountCreationFailedEvent(command, message));
    }

    private AccountCreationFailed accountCreationFailedEvent(CreateAccountCommand command, String message) {
        return new AccountCreationFailed(UUID.randomUUID(), command.userName(), command.email(), message);
    }
}
