package jah.catchflight.engagement.port.in;

import jah.catchflight.common.annotations.hexagonal.InboundPort;
import jah.catchflight.sharedkernel.account.AccountId;

import java.util.Objects;

@InboundPort
public interface CaptureUserActivityUseCase {
    CaptureUserActivityResult captureUserActivity(CaptureUserActivityCommand captureUserActivityCommand);

    record CaptureUserActivityCommand(AccountId accountId) {
        public CaptureUserActivityCommand {
            Objects.requireNonNull(accountId);
        }
    }

    record CaptureUserActivityResult() {}
}
