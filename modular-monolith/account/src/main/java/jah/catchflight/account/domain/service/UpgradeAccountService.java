package jah.catchflight.account.domain.service;

import jah.catchflight.account.port.in.UpgradeAccountUseCase;
import jah.catchflight.account.port.out.AccountEventPublisher;
import jah.catchflight.account.port.out.FindAccountRepository;
import jah.catchflight.account.port.out.UpdateAccountRepository;
import jah.catchflight.common.annotations.domain.DomainService;
import lombok.RequiredArgsConstructor;

/**
 * Service responsible for handling user account upgrades.
 * This class implements the {@link UpgradeAccountUseCase} interface to provide
 * the business logic for upgrading user accounts, utilizing repositories for
 * account retrieval and updates, and an event publisher for notifications.
 */
@DomainService
@RequiredArgsConstructor
public class UpgradeAccountService implements UpgradeAccountUseCase {
    private final FindAccountRepository findAccountRepository;
    private final UpdateAccountRepository updateAccountRepository;
    private final AccountEventPublisher accountEventPublisher;

    /**
     * Upgrades a user account based on the provided command.
     *
     * @param command the {@link UpgradeUserCommand} containing the details required for the account upgrade
     * @return an {@link UpgradeUserResult} representing the outcome of the account upgrade process
     * @throws UnsupportedOperationException if the operation is not yet implemented
     */
    @Override
    public UpgradeUserResult upgradeUser(UpgradeUserCommand command) {
        throw new UnsupportedOperationException();
    }
}
