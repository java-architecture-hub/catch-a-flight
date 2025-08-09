package jah.catchflight.account.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.UserId;

/**
 * Defines the inbound port for user sign-in operations.
 * This interface specifies the contract for authenticating users,
 * allowing clients to initiate the sign-in process with the required credentials.
 */
@InboundPort
public interface SignInUseCase {
    /**
     * Authenticates a user based on the provided sign-in command.
     *
     * @param signInCommand the {@link SignInCommand} containing the credentials for sign-in
     * @return a {@link SignInResult} indicating the outcome of the sign-in process
     */
    SignInResult signIn(SignInCommand signInCommand);

    /**
     * A record representing the command to initiate a user sign-in.
     * This record is currently empty, but can be extended to include authentication details
     * such as username and password.
     */
    record SignInCommand() {}

    /**
     * A sealed interface representing the possible outcomes of the sign-in process.
     * It defines the various result types for success and failure scenarios.
     */
    sealed interface SignInResult {
        record Success(UserId userId) implements SignInResult {}
        record AuthenticationFailure() implements SignInResult {}
        record InternalFailure(Throwable cause) implements SignInResult {}
    }
}
