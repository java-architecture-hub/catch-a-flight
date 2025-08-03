package jah.catchflight.account.port.out;

import jah.catchflight.account.domain.model.CurrentAccount;
import jah.catchflight.common.annotations.domain.DomainRepository;
import jah.catchflight.common.annotations.hexagonal.OutboundPort;
import jah.catchflight.sharedkernel.account.Email;

@OutboundPort
@DomainRepository
public interface FindCurrentAccountRepository {
    CurrentAccount findByEmail(Email email);
}
