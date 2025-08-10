package jah.catchflight.engagement.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundAdapter;
import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.UserId;

import java.util.Objects;

@InboundPort
public interface CaptureUserActivityUseCase {
    CaptureUserActivityResult captureUserActivity(CaptureUserActivityCommand captureUserActivityCommand);

    record CaptureUserActivityCommand(UserId userId) {
        public CaptureUserActivityCommand {
            Objects.requireNonNull(userId);
        }
    }

    record CaptureUserActivityResult() {}
}
