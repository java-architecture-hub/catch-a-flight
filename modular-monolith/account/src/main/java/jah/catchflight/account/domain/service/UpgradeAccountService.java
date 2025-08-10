package jah.catchflight.account.domain.service;

import jah.catchflight.account.domain.events.AccountUpgradeFailed;
import jah.catchflight.account.domain.events.AccountUpgraded;
import jah.catchflight.account.port.in.UpgradeAccountUseCase;
import jah.catchflight.account.port.out.AccountEventPublisher;
import jah.catchflight.account.port.out.FindAccountRepository;
import jah.catchflight.account.port.out.UpdateAccountRepository;
import jah.catchflight.common.annotations.domain.DomainService;
import jah.catchflight.common.validation.InputValidationResult;
import jah.catchflight.sharedkernel.account.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static jah.catchflight.account.port.in.UpgradeAccountUseCase.UpgradeUserResult.*;
import static jah.catchflight.common.validation.InputValidationResult.NotValid;

/**
 * Service responsible for handling user account upgrades.
 * This class implements the {@link UpgradeAccountUseCase} interface to provide
 * the business logic for upgrading user accounts, utilizing repositories for
 * account retrieval and updates, and an event publisher for notifications.
 */
@Service
@DomainService
@RequiredArgsConstructor
public class UpgradeAccountService implements UpgradeAccountUseCase {
    private static final String ACCOUNT_ALREADY_UPGRADED = "Account has been already upgraded";
    private static final String ACCOUNT_NOT_FOUND = "Account not found";
    private final UpgradeCommandValidator upgradeCommandValidator;
    private final FindAccountRepository findAccountRepository;
    private final UpdateAccountRepository updateAccountRepository;
    private final AccountEventPublisher accountEventPublisher;

    /**
     * Upgrades a user account based on the provided command.
     *
     * @param command the {@link UpgradeUserCommand} containing the details required for the account upgrade
     * @return an {@link UpgradeUserResult} representing the outcome of the account upgrade process
     */
    @Override
    public UpgradeUserResult upgradeUser(final UpgradeUserCommand command) {
        try {
            // Input validation
            var validationResult = upgradeCommandValidator.validate(command);
            if (validationResult instanceof NotValid(String message)) {
                return new InputNotValid(message);
            }
            // Core logic
            var optionalAccount = findAccountRepository.load(command.userId());
            if (optionalAccount.isPresent()) {
                var account = optionalAccount.get();
                if (account.isPremium()) {
                    // Handle fail result
                    emitAccountUpgradeFailed(command.userId(), ACCOUNT_ALREADY_UPGRADED);
                    return new AccountAlreadyUpgradedFailure(ACCOUNT_ALREADY_UPGRADED);
                }
                account.upgradeUser();
                updateAccountRepository.save(account);
                // Handle success result
                emitAccountUpgraded(command.userId());
                return new Success();
            } else {
                // Handle fail result
                emitAccountUpgradeFailed(command.userId(), ACCOUNT_NOT_FOUND);
                return new AccountNotFoundFailure(ACCOUNT_NOT_FOUND);
            }
        } catch (Exception ex) {
            // Handle fail result
            emitAccountUpgradeFailed(command.userId(), ex.getMessage());
            return new InternalFailure(ex);
        }
    }

    /**
     * Publishes an event indicating a successful user account upgrade.
     */
    private void emitAccountUpgraded(UserId userId) {
        accountEventPublisher.publish(new AccountUpgraded(UUID.randomUUID(), userId));
    }

    /**
     * Publishes an event indicating a failed user account upgrade.
     */
    private void emitAccountUpgradeFailed(final UserId userId, final String message) {
        accountEventPublisher.publish(new AccountUpgradeFailed(UUID.randomUUID(), userId, message));
    }

    /**
     * Validates the {@link UpgradeUserCommand} to ensure it meets the required criteria for upgrading a user.
     */
    private class UpgradeCommandValidator {
        InputValidationResult validate(final UpgradeUserCommand upgradeUserCommand) {
            return new InputValidationResult.Valid();
        }
    }
}
