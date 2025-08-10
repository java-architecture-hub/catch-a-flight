package jah.catchflight.account.domain.service;

import jah.catchflight.account.domain.events.AccountUpgradeFailed;
import jah.catchflight.account.domain.events.AccountUpgraded;
import jah.catchflight.account.domain.model.Account;
import jah.catchflight.account.port.in.UpgradeAccountUseCase;
import jah.catchflight.account.port.out.AccountEventPublisher;
import jah.catchflight.account.port.out.FindAccountRepository;
import jah.catchflight.account.port.out.UpdateAccountRepository;
import jah.catchflight.common.annotations.domain.DomainService;
import jah.catchflight.common.validation.InputValidationResult;
import jah.catchflight.sharedkernel.account.UserId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

import static jah.catchflight.account.port.in.UpgradeAccountUseCase.UpgradeUserResult.*;
import static jah.catchflight.common.validation.InputValidationResult.NotValid;
import static jah.catchflight.common.validation.InputValidationResult.Valid;

/**
 * Domain service responsible for handling user account upgrades.
 * Implements {@link UpgradeAccountUseCase} to encapsulate the business logic for upgrading user accounts.
 */
@Slf4j
@Service
@DomainService
@RequiredArgsConstructor
public class UpgradeAccountService implements UpgradeAccountUseCase {
    private static final String ACCOUNT_ALREADY_UPGRADED = "Account is already upgraded";
    private static final String ACCOUNT_NOT_FOUND = "Account not found";

    private final UpgradeCommandValidator upgradeCommandValidator;
    private final FindAccountRepository findAccountRepository;
    private final UpdateAccountRepository updateAccountRepository;
    private final AccountEventPublisher accountEventPublisher;

    /**
     * Upgrades a user account based on the provided command.
     *
     * @param command the {@link UpgradeUserCommand} containing details for the account upgrade
     * @return an {@link UpgradeUserResult} representing the outcome of the upgrade process
     * @throws IllegalArgumentException if the command is null
     */
    @Override
    public UpgradeUserResult upgradeUser(final UpgradeUserCommand command) {
        if (command == null) {
            log.error("UpgradeUserCommand is null");
            throw new IllegalArgumentException("UpgradeUserCommand cannot be null");
        }

        try {
            // Validate input
            var validationResult = upgradeCommandValidator.validate(command);
            if (validationResult instanceof NotValid(String message)) {
                log.warn("Invalid upgrade command for userId: {}, reason: {}", command.userId(), message);
                return new InputNotValid(message);
            }

            // Retrieve and process account
            return findAccountRepository.load(command.userId())
                    .map(account -> processAccountUpgrade(account, command.userId()))
                    .orElseGet(() -> handleAccountNotFound(command.userId()));
        } catch (Exception ex) {
            log.error("Unexpected error during account upgrade for userId: {}", command.userId(), ex);
            return handleInternalFailure(command.userId(), ex);
        }
    }

    /**
     * Processes the account upgrade logic.
     */
    private UpgradeUserResult processAccountUpgrade(Account account, UserId userId) {
        if (account.isPremium()) {
            log.info("Account upgrade failed for userId: {} - already upgraded", userId);
            emitAccountUpgradeFailed(userId, ACCOUNT_ALREADY_UPGRADED);
            return new AccountAlreadyUpgradedFailure(ACCOUNT_ALREADY_UPGRADED);
        }

        account.upgradeUser();
        updateAccountRepository.save(account);
        log.info("Account upgraded successfully for userId: {}", userId);
        emitAccountUpgraded(userId);
        return new Success();
    }

    /**
     * Handles the case when an account is not found.
     */
    private UpgradeUserResult handleAccountNotFound(UserId userId) {
        log.warn("Account not found for userId: {}", userId);
        emitAccountUpgradeFailed(userId, ACCOUNT_NOT_FOUND);
        return new AccountNotFoundFailure(ACCOUNT_NOT_FOUND);
    }

    /**
     * Handles unexpected errors during the upgrade process.
     */
    private UpgradeUserResult handleInternalFailure(UserId userId, Exception ex) {
        emitAccountUpgradeFailed(userId, ex.getMessage());
        return new InternalFailure(ex);
    }

    /**
     * Publishes an event for a successful account upgrade.
     */
    private void emitAccountUpgraded(UserId userId) {
        accountEventPublisher.publish(new AccountUpgraded(UUID.randomUUID(), userId));
    }

    /**
     * Publishes an event for a failed account upgrade.
     */
    private void emitAccountUpgradeFailed(UserId userId, String message) {
        accountEventPublisher.publish(new AccountUpgradeFailed(UUID.randomUUID(), userId, message));
    }

    /**
     * Validates the {@link UpgradeUserCommand} to ensure it meets upgrade criteria.
     */
    static class UpgradeCommandValidator {
        InputValidationResult validate(final UpgradeUserCommand command) {
            if (command.userId() == null) {
                return new NotValid("User ID cannot be null");
            }
            return new Valid();
        }
    }
}
