package jah.catchflight.booking.domain;

import jah.catchflight.booking.port.in.DeleteAccountUseCase;
import jah.catchflight.common.annotations.domain.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@DomainService
@RequiredArgsConstructor
public class DeleteAccountService implements DeleteAccountUseCase {
    @Override
    public DeleteAccountResult deleteAccount(DeleteAccountCommand deleteUserInput) {
        throw new UnsupportedOperationException();
    }
}
