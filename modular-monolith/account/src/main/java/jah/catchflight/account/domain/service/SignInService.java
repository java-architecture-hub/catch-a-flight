package jah.catchflight.account.domain.service;

import jah.catchflight.account.port.in.SignInUseCase;
import jah.catchflight.account.port.out.AccountEventPublisher;
import jah.catchflight.common.annotations.domain.DomainService;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class SignInService implements SignInUseCase {
    private final AccountEventPublisher accountEventPublisher;

    @Override
    public SignInResult signIn(SignInCommand signInCommand) {
        throw new UnsupportedOperationException();
    }
}
