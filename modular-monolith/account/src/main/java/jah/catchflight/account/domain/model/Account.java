package jah.catchflight.account.domain.model;

import jah.catchflight.common.annotations.domain.DomainAggregateRoot;
import jah.catchflight.common.persistence.Version;
import jah.catchflight.sharedkernel.account.AccountType;
import jah.catchflight.sharedkernel.account.Email;
import jah.catchflight.sharedkernel.account.UserId;
import lombok.Builder;
import lombok.Getter;

/**
 * Represents an account entity in the system, serving as a domain aggregate root.
 * This class encapsulates user-related information such as user ID, email, password, username,
 * account type, and version. It provides functionality to upgrade a user's account type.
 */
@Getter
@Builder
@DomainAggregateRoot
public class Account {
    private UserId userId;
    private Email email;
    private Password password;
    private UserName userName;
    private AccountType accountType;
    private Version version;

    /**
     * Upgrades the user's account type from REGULAR to PREMIUM.
     * If the account is already PREMIUM, the upgrade attempt will fail.
     *
     * @return an {@link UpgradeUserResult} indicating the result of the upgrade attempt
     */
    public UpgradeUserResult upgradeUser() {
        return switch (accountType) {
            case PREMIUM -> new UpgradeUserResult.AlreadyUpdatedFailure(userId, "Premium user can't be upgraded");
            case REGULAR -> {
                accountType = AccountType.PREMIUM;
                yield new UpgradeUserResult.Success();
            }
        };
    }

    /**
     * Sealed interface representing the result of an account upgrade operation.
     */
    public sealed interface UpgradeUserResult {
        record Success() implements UpgradeUserResult {}
        record AlreadyUpdatedFailure(UserId userId, String message) implements UpgradeUserResult {}
    }
}
