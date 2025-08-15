package jah.catchflight.booking.domain;

import jah.catchflight.booking.port.in.CreateAccountUseCase;
import jah.catchflight.common.annotations.domain.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * A service responsible for handling user account creation in the system.
 * <p>
 * This class implements the {@link CreateAccountUseCase} interface to encapsulate the
 * business logic for creating user accounts based on a provided command. It is annotated
 * as a Spring {@code @Service} with a specific bean name and a domain service to indicate
 * its role in the application layer and domain logic. Successful account creation may
 * result in a {@link UserActivity#SIGN_IN} activity being recorded.
 * </p>
 *
 * @see CreateAccountUseCase
 * @see CreateAccountCommand
 * @see CreateAccountResult
 * @see UserActivity
 */
@DomainService
@RequiredArgsConstructor
@Service(CreateAccountService.BEAN_NAME)
public class CreateAccountService implements CreateAccountUseCase {
    public static final String BEAN_NAME = "bookingCreateAccountService";

    /**
     * {@inheritDoc}
     */
    @Override
    public CreateAccountResult createUser(CreateAccountCommand command) {
        throw new UnsupportedOperationException();
    }
}
