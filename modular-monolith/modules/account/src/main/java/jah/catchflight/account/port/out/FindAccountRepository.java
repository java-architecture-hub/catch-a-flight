package jah.catchflight.account.port.out;

import jah.catchflight.account.domain.Account;
import jah.catchflight.common.annotations.domain.DomainRepository;
import jah.catchflight.common.annotations.hexagonal.OutboundPort;
import jah.catchflight.sharedkernel.account.AccountId;

import java.util.Optional;

/**
 * Defines the outbound port for retrieving user accounts from the data store. This functional
 * interface serves as a domain repository, specifying the contract for loading an account entity
 * based on its unique identifier.
 */
@OutboundPort
@DomainRepository
@FunctionalInterface
public interface FindAccountRepository {

	Optional<Account> load(AccountId accountId);
}
