package jah.catchflight.account.domain.service;

import jah.catchflight.account.domain.model.AccountFactory;
import jah.catchflight.account.port.in.CreateAccountUseCase;
import jah.catchflight.account.port.out.AccountEventPublisher;
import jah.catchflight.account.port.out.CreateAccountRepository;
import jah.catchflight.common.annotations.domain.DomainService;
import lombok.RequiredArgsConstructor;

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
     *
     * @param command the {@link CreateAccountCommand} containing the necessary information for account creation
     * @return a {@link CreateAccountResult} representing the outcome of the account creation process
     * @throws UnsupportedOperationException if the operation is not yet implemented
     */
    @Override
    public CreateAccountResult createUser(CreateAccountCommand command) {
        throw new UnsupportedOperationException();
    }
}
