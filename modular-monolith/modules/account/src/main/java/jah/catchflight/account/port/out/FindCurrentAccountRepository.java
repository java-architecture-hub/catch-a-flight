package jah.catchflight.account.port.out;

import jah.catchflight.account.domain.CurrentAccount;
import jah.catchflight.common.annotations.domain.DomainRepository;
import jah.catchflight.common.annotations.hexagonal.OutboundPort;
import jah.catchflight.sharedkernel.account.Email;

/**
 * Defines the outbound port for retrieving the current user account from the data store.
 * This interface serves as a domain repository, specifying the contract for finding
 * an account entity based on its associated email address.
 */
@OutboundPort
@DomainRepository
public interface FindCurrentAccountRepository {
    /**
     * Retrieves the current account associated with the specified email address.
     *
     * @param email the {@link Email} uniquely identifying the account to retrieve
     * @return the {@link CurrentAccount} associated with the provided email
     */
    CurrentAccount findByEmail(Email email);
}
