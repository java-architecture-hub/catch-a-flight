package jah.catchflight.account.domain;

import jah.catchflight.account.port.in.UpgradeAccountUseCase;
import jah.catchflight.account.port.out.AccountEventPublisher;
import jah.catchflight.account.port.out.FindAccountRepository;
import jah.catchflight.account.port.out.UpdateAccountRepository;
import jah.catchflight.common.annotations.domain.DomainService;
import jah.catchflight.event.account.AccountUpgradeFailed;
import jah.catchflight.event.account.AccountUpgraded;
import jah.catchflight.sharedkernel.account.AccountId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

import static jah.catchflight.account.domain.Account.UpgradeUserResult.AlreadyUpdatedFailure;
import static jah.catchflight.account.domain.Account.UpgradeUserResult.UserUpgradedSuccess;
import static jah.catchflight.account.port.in.UpgradeAccountUseCase.UpgradeUserResult.*;

/**
 * Domain service responsible for handling user account upgrades. Implements {@link
 * UpgradeAccountUseCase} to encapsulate the business logic for upgrading user accounts.
 */
@Slf4j
@Service
@DomainService
@RequiredArgsConstructor
public class UpgradeAccountService implements UpgradeAccountUseCase {
    private static final String ACCOUNT_NOT_FOUND = "Account not found";

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
        Objects.requireNonNull(command, "UpgradeUserCommand cannot be null");

        try {
            return findAccountRepository
                .load(command.accountId())
                .map(account -> processAccountUpgrade(account, command.accountId()))
                .orElseGet(() -> handleAccountNotFound(command.accountId()));
        } catch (Exception ex) {
            return handleInternalFailure(command.accountId(), ex);
        }
    }

    /**
     * Processes the account upgrade logic.
     */
    private UpgradeUserResult processAccountUpgrade(Account account, AccountId accountId) {
        var result = account.upgradeUser();

        return switch (result) {
            case UserUpgradedSuccess() -> {
                updateAccountRepository.save(account);
                emitAccountUpgraded(accountId);
                log.info("Account upgraded successfully for userId: {}", accountId);
                yield Success.builder().build();
            }

            case AlreadyUpdatedFailure(_, String message) -> {
                emitAccountUpgradeFailed(accountId, message);
                log.warn("Account  failed for userId: {}. Reason: {}", accountId, message);
                yield AccountAlreadyUpgradedFailure.builder().message(message).build();
            }
        };
    }

    /**
     * Handles the case when an account is not found.
     */
    private UpgradeUserResult handleAccountNotFound(AccountId accountId) {
        log.warn("Account not found for userId: {}", accountId);
        emitAccountUpgradeFailed(accountId, ACCOUNT_NOT_FOUND);
        return new AccountNotFoundFailure(ACCOUNT_NOT_FOUND);
    }

    /**
     * Handles unexpected errors during the upgrade process.
     */
    private UpgradeUserResult handleInternalFailure(AccountId accountId, Exception ex) {
        log.error("Unexpected error during account upgrade for userId: {}", accountId, ex);
        emitAccountUpgradeFailed(accountId, ex.getMessage());
        return new InternalFailure(ex);
    }

    /**
     * Publishes an event for a successful account upgrade.
     */
    private void emitAccountUpgraded(AccountId accountId) {
        accountEventPublisher.publish(new AccountUpgraded(
            UUID.randomUUID(),
            accountId
        ));
    }

    /**
     * Publishes an event for a failed account upgrade.
     */
    private void emitAccountUpgradeFailed(AccountId accountId, String message) {
        accountEventPublisher.publish(
            new AccountUpgradeFailed(
                UUID.randomUUID(),
                accountId,
                message
            ));
    }
}
