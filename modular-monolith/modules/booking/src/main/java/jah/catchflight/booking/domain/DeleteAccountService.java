package jah.catchflight.booking.domain;

import jah.catchflight.booking.port.in.DeleteAccountUseCase;
import jah.catchflight.common.annotations.domain.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * A service responsible for handling user account deletion in the system.
 * <p>
 * This class implements the {@link DeleteAccountUseCase} interface to encapsulate the
 * business logic for deleting user accounts based on a provided command. It is annotated
 * as a Spring {@code @Service} and a domain service to indicate its role in the application
 * layer and domain logic. Successful account deletion may result in a
 * {@link UserActivity#SIGN_OUT} activity being recorded via {@link CaptureUserActivityService}.
 * </p>
 *
 * @see DeleteAccountUseCase
 * @see DeleteAccountCommand
 * @see DeleteAccountResult
 * @see UserActivity
 * @see CaptureUserActivityService
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
