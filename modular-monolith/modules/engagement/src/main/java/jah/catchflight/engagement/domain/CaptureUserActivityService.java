package jah.catchflight.engagement.domain;

import jah.catchflight.common.annotations.domain.DomainService;
import jah.catchflight.engagement.port.in.CaptureUserActivityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * A service responsible for capturing user activity in the system.
 */
@Service
@DomainService
@RequiredArgsConstructor
public class CaptureUserActivityService implements CaptureUserActivityUseCase {
    /**
     * Captures user activity based on the provided command.
     */
    @Override
    public CaptureUserActivityResult captureUserActivity(CaptureUserActivityCommand captureUserActivityCommand) {
        throw new UnsupportedOperationException();
    }
}
