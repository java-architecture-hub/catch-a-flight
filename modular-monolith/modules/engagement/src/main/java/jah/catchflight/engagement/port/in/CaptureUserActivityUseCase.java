package jah.catchflight.engagement.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.engagement.domain.UserActivity;
import jah.catchflight.sharedkernel.account.AccountId;

import java.util.Objects;

/**
 * Defines a use case for capturing user activity in the system.
 * This interface provides a contract for recording user activity
 * based on a provided command, returning the result of the operation.
 */
@InboundPort
public interface CaptureUserActivityUseCase {

    /**
     * Captures user activity based on the provided command.
     */
    CaptureUserActivityResult captureUserActivity(CaptureUserActivityCommand captureUserActivityCommand);

    /**
     * Represents the result of a user activity capture operation.
     * This sealed interface defines the possible outcomes of an activity capture attempt.
     */
    sealed interface CaptureUserActivityResult {
        record Success() implements CaptureUserActivityResult {}
        record InternalFailure(Throwable cause) implements CaptureUserActivityResult {}
    }
    /**
     * A command to capture user activity, containing the account ID and user activity details.
     */
    record CaptureUserActivityCommand(AccountId accountId, UserActivity userActivity) {
        public CaptureUserActivityCommand {
            Objects.requireNonNull(accountId, "accountId must not be null");
            Objects.requireNonNull(userActivity, "userActivity must not be null");
        }
    }
}
