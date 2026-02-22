package jah.catchflight.account.domain;

import static jah.catchflight.account.domain.Account.UpgradeUserResult.AlreadyUpdatedFailure;
import static jah.catchflight.account.domain.Account.UpgradeUserResult.UserUpgradedSuccess;

import jah.catchflight.common.annotations.domain.DomainAggregateRoot;
import jah.catchflight.common.persistence.Version;
import jah.catchflight.sharedkernel.account.AccountId;
import jah.catchflight.sharedkernel.account.AccountType;
import jah.catchflight.sharedkernel.account.Email;
import jah.catchflight.sharedkernel.account.UserName;

import lombok.Builder;
import lombok.Getter;

/**
 * Represents an account entity in the system, serving as a domain aggregate root. This class
 * encapsulates user-related information such as user ID, email, password, username, account type,
 * and version. It provides functionality to upgrade a user's account type.
 */
@Getter
@Builder
@DomainAggregateRoot
public class Account {

	private AccountId accountId;
	private Email email;
	private Password password;
	private UserName userName;
	private AccountType accountType;
	private Version version;

	/**
	 * Upgrades the user's account type from REGULAR to PREMIUM. If the account is already PREMIUM,
	 * the upgrade attempt will fail.
	 *
	 * @return an {@link UpgradeUserResult} indicating the result of the upgrade attempt
	 */
	public UpgradeUserResult upgradeUser() {
		return switch (accountType) {
			case PREMIUM -> {
				yield AlreadyUpdatedFailure.builder()
						.accountId(accountId)
						.message("Premium user can't be upgraded")
						.build();
			}
			case REGULAR -> {
				accountType = AccountType.PREMIUM;
				yield UserUpgradedSuccess.builder().build();
			}
		};
	}

	/** Checks if the account is of premium type. */
	public boolean isPremium() {
		return accountType == AccountType.PREMIUM;
	}

	/** Sealed interface representing the result of an account upgrade operation. */
	public sealed interface UpgradeUserResult {

		@Builder
		record UserUpgradedSuccess() implements UpgradeUserResult {}

		@Builder
		record AlreadyUpdatedFailure(AccountId accountId, String message)
				implements UpgradeUserResult {}
	}
}
