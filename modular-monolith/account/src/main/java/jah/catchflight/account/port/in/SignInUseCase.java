package jah.catchflight.account.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.UserId;

@InboundPort
public interface SignInUseCase {
    SignInResult signIn(SignInCommand signInCommand);

    record SignInCommand() {}

    sealed interface SignInResult {
        record Success(UserId userId) implements SignInResult {}
        record AuthenticationFailure() implements SignInResult {}
        record InternalFailure(Throwable cause) implements SignInResult {}
    }
}
