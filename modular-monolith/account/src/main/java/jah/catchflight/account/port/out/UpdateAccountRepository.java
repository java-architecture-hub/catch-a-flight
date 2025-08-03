package jah.catchflight.account.port.out;

import jah.catchflight.account.domain.model.Account;
import jah.catchflight.common.annotations.domain.DomainRepository;
import jah.catchflight.common.annotations.hexagonal.OutboundPort;

/**
 * Defines the outbound port for updating user accounts in the data store.
 * This functional interface serves as a domain repository, specifying the contract
 * for persisting changes to an existing account entity.
 */
@OutboundPort
@DomainRepository
@FunctionalInterface
public interface UpdateAccountRepository {
    /**
     * Saves changes to an existing account in the data store.
     *
     * @param account the {@link Account} entity with updated information to be persisted
     */
    void save(Account account);
}
