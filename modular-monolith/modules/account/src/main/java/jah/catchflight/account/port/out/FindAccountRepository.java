package jah.catchflight.account.port.out;

import jah.catchflight.account.domain.model.Account;
import jah.catchflight.common.annotations.domain.DomainRepository;
import jah.catchflight.common.annotations.hexagonal.OutboundPort;
import jah.catchflight.sharedkernel.account.AccountId;

import java.util.Optional;

/**
 * Defines the outbound port for retrieving user accounts from the data store.
 * This functional interface serves as a domain repository, specifying the contract
 * for loading an account entity based on its unique identifier.
 */
@OutboundPort
@DomainRepository
@FunctionalInterface
public interface FindAccountRepository {
    /**
     * Loads an account from the data store based on the provided user ID.
     *
     * @param accountId the {@link AccountId} uniquely identifying the account to retrieve
     * @return an {@link Optional} containing the {@link Account} if found, or an empty {@link Optional} if no account exists
     */
    Optional<Account> load(AccountId accountId);
}
