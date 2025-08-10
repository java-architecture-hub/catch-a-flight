package jah.catchflight.booking.domain;

import jah.catchflight.booking.port.in.CreateAccountUseCase;
import jah.catchflight.common.annotations.domain.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@DomainService
@RequiredArgsConstructor
public class CreateAccountService implements CreateAccountUseCase {
    @Override
    public CreateAccountResult createUser(CreateAccountCommand command) {
        throw new UnsupportedOperationException();
    }
}
