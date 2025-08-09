package jah.catchflight.account.domain.service;

import jah.catchflight.account.domain.events.AccountUpgradeFailed;
import jah.catchflight.account.domain.events.AccountUpgraded;
import jah.catchflight.account.domain.model.AccountAlreadyUpgradedException;
import jah.catchflight.account.port.in.UpgradeAccountUseCase;
import jah.catchflight.account.port.out.AccountEventPublisher;
import jah.catchflight.account.port.out.FindAccountRepository;
import jah.catchflight.account.port.out.UpdateAccountRepository;
import jah.catchflight.common.annotations.domain.DomainService;
import jah.catchflight.sharedkernel.account.UserId;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static jah.catchflight.account.port.in.UpgradeAccountUseCase.UpgradeUserResult.*;

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
        try {
            var userOptional = findAccountRepository.load(command.userId());
            if (userOptional.isPresent()) {
                var user = userOptional.get();
                user.upgradeUser();
                updateAccountRepository.save(user);
                emitUserUpgraded(command.userId());
                return new Success();
            } else {
                return new UserNotFoundFailure("User not found");
            }
        } catch (AccountAlreadyUpgradedException ex) {
            emitUserUpgradeFailed(command.userId(), ex.getMessage());
            return new UserAlreadyUpgradedFailure(ex.getMessage());
        } catch (Exception ex) {
            emitUserUpgradeFailed(command.userId(), ex.getMessage());
            return new InternalFailure(ex);
        }
    }

    /**
     * Publishes an event indicating a successful user account upgrade.
     *
     * @param userId the {@link UserId} of the user whose account was upgraded
     */
    private void emitUserUpgraded(UserId userId) {
        accountEventPublisher.publish(userUpgradedEvent(userId));
    }

    /**
     * Publishes an event indicating a failed user account upgrade.
     *
     * @param userId  the {@link UserId} of the user whose account upgrade failed
     * @param message the error message describing the reason for the failure
     */
    private void emitUserUpgradeFailed(UserId userId, String message) {
        accountEventPublisher.publish(userUpgradedFailedEvent(userId, message));
    }

    /**
     * Creates an event representing a successful user account upgrade.
     *
     * @param userId the {@link UserId} of the user whose account was upgraded
     * @return an {@link AccountUpgraded} event containing the upgrade details
     */
    private AccountUpgraded userUpgradedEvent(UserId userId) {
        return new AccountUpgraded(UUID.randomUUID(), userId);
    }

    /**
     * Creates an event representing a failed user account upgrade.
     *
     * @param userId  the {@link UserId} of the user whose account upgrade failed
     * @param message the error message describing the reason for the failure
     * @return an {@link AccountUpgradeFailed} event containing the failure details
     */
    private AccountUpgradeFailed userUpgradedFailedEvent(UserId userId, String message) {
        return new AccountUpgradeFailed(UUID.randomUUID(), userId, message);
    }
}