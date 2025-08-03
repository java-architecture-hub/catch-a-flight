package jah.catchflight.account.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.UserId;

import java.util.Objects;

/**
 * Defines the inbound port for upgrading user accounts.
 * This interface specifies the contract for the account upgrade use case,
 * allowing clients to initiate the process of upgrading a user account with the required details.
 */
@InboundPort
public interface UpgradeAccountUseCase {
    /**
     * Upgrades a user account based on the provided command.
     *
     * @param command the {@link UpgradeUserCommand} containing the user ID for the account to be upgraded
     * @return an {@link UpgradeUserResult} indicating the outcome of the account upgrade process
     */
    UpgradeUserResult upgradeUser(UpgradeUserCommand command);

    /**
     * A record representing the command to upgrade a user account.
     * It encapsulates the user ID of the account to be upgraded.
     *
     * @param userId the unique identifier of the user account to upgrade
     */
    record UpgradeUserCommand(UserId userId) {
        /**
         * Constructs a new {@link UpgradeUserCommand} with validation.
         * Ensures that the provided user ID is not null.
         *
         * @param userId the user ID, must not be null
         * @throws NullPointerException if the user ID is null
         */
        public UpgradeUserCommand {
            Objects.requireNonNull(userId);
        }
    }

    /**
     * A sealed interface representing the possible outcomes of the account upgrade process.
     * It defines the various result types for success and failure scenarios.
     */
    sealed interface UpgradeUserResult {
        /**
         * Represents a successful account upgrade.
         */
        record Success() implements UpgradeUserResult {}

        /**
         * Represents a failure due to the user account not being found.
         *
         * @param message the error message describing the failure
         */
        record UserNotFoundFailure(String message) implements UpgradeUserResult {}

        /**
         * Represents a failure due to the user account already being upgraded.
         *
         * @param message the error message describing the failure
         */
        record UserAlreadyUpgradedFailure(String message) implements UpgradeUserResult {}

        /**
         * Represents an internal failure during the account upgrade process.
         *
         * @param cause the throwable cause of the failure
         */
        record InternalFailure(Throwable cause) implements UpgradeUserResult {}
    }
}
