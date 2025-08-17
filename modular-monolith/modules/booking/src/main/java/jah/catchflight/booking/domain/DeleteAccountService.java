package jah.catchflight.booking.domain;

import jah.catchflight.booking.port.in.DeleteAccountUseCase;
import jah.catchflight.common.annotations.domain.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * A service responsible for handling user account deletion in the system.
 */
@Service
@DomainService
@RequiredArgsConstructor
public class DeleteAccountService implements DeleteAccountUseCase {
    /**
     * {@inheritDoc}
     */
    @Override
    public DeleteAccountResult deleteAccount(DeleteAccountCommand deleteUserInput) {
        throw new UnsupportedOperationException();
    }
}
