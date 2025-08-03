package jah.catchflight.account.domain.model;

import jah.catchflight.common.annotations.domain.DomainAggregateRoot;
import jah.catchflight.common.persistence.Version;
import jah.catchflight.sharedkernel.account.AccountType;
import jah.catchflight.sharedkernel.account.Email;
import jah.catchflight.sharedkernel.account.UserId;
import lombok.Builder;
import lombok.Getter;

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

    public UpgradeUserResult upgradeUser() {
        return switch (accountType) {
            case PREMIUM -> new UpgradeUserResult.AlreadyUpdatedFailure(userId, "Premium user can't be upgraded");
            case REGULAR -> {
                accountType = AccountType.PREMIUM;
                yield new UpgradeUserResult.Success();
            }
        };
    }

    public sealed interface UpgradeUserResult {
        record Success() implements UpgradeUserResult {}
        record AlreadyUpdatedFailure(UserId userId, String message) implements UpgradeUserResult {}
    }
}
