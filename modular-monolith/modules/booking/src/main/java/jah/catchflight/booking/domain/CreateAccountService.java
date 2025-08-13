package jah.catchflight.booking.domain;

import jah.catchflight.booking.port.in.CreateAccountUseCase;
import jah.catchflight.common.annotations.domain.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@DomainService
@RequiredArgsConstructor
@Service(CreateAccountService.BEAN_NAME)
public class CreateAccountService implements CreateAccountUseCase {
    public static final String BEAN_NAME = "bookingCreateAccountService";

    @Override
    public CreateAccountResult createUser(CreateAccountCommand command) {
        throw new UnsupportedOperationException();
    }
}
