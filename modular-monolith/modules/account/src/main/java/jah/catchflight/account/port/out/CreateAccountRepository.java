package jah.catchflight.account.port.out;

import jah.catchflight.account.domain.Account;
import jah.catchflight.common.annotations.domain.DomainRepository;
import jah.catchflight.common.annotations.hexagonal.OutboundPort;

/**
 * Defines the outbound port for creating and persisting user accounts in the system.
 * This functional interface serves as a domain repository, specifying the contract
 * for storing new account entities in the underlying data store.
 */
@OutboundPort
@DomainRepository
@FunctionalInterface
public interface CreateAccountRepository {
    /**
     * Creates and persists a new account in the data store.
     *
     * @param account the {@link Account} entity to be created and stored
     * @return the persisted {@link Account} entity, potentially enriched with additional data (e.g., generated ID)
     */
    Account create(Account account);
}
