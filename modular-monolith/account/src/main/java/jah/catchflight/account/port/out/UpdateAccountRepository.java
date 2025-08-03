package jah.catchflight.account.port.out;

import jah.catchflight.account.domain.model.Account;
import jah.catchflight.common.annotations.domain.DomainRepository;
import jah.catchflight.common.annotations.hexagonal.OutboundPort;

@OutboundPort
@DomainRepository
@FunctionalInterface
public interface UpdateAccountRepository {
    void save(Account account);
}
