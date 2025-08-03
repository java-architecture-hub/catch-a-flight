package jah.catchflight.account.port.out;

import jah.catchflight.account.domain.model.Account;
import jah.catchflight.common.annotations.domain.DomainRepository;
import jah.catchflight.common.annotations.hexagonal.OutboundPort;
import jah.catchflight.sharedkernel.account.UserId;

import java.util.Optional;

@OutboundPort
@DomainRepository
@FunctionalInterface
public interface FindAccountRepository {
    Optional<Account> load(UserId userId);
}
