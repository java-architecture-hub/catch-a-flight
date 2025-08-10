package jah.catchflight.account.domain;

import jah.catchflight.account.port.in.SignInUseCase;
import jah.catchflight.account.port.out.AccountEventPublisher;
import jah.catchflight.common.annotations.domain.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service responsible for handling user sign-in operations.
 * This class implements the {@link SignInUseCase} interface to provide
 * the business logic for user authentication, utilizing an event publisher
 * for notifications related to sign-in events.
 */
@Service
@DomainService
@RequiredArgsConstructor
public class SignInService implements SignInUseCase {
    private final AccountEventPublisher accountEventPublisher;

    /**
     * Authenticates a user based on the provided sign-in command.
     *
     * @param signInCommand the {@link SignInCommand} containing the credentials and details for sign-in
     * @return a {@link SignInResult} representing the outcome of the sign-in process
     * @throws UnsupportedOperationException if the operation is not yet implemented
     */
    @Override
    public SignInResult signIn(SignInCommand signInCommand) {
        throw new UnsupportedOperationException();
    }
}
