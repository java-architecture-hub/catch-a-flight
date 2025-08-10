package jah.catchflight.engagement.domain;

import jah.catchflight.common.annotations.domain.DomainService;
import jah.catchflight.engagement.port.in.CaptureUserActivityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@DomainService
@RequiredArgsConstructor
public class CaptureUserActivityService implements CaptureUserActivityUseCase {
    @Override
    public CaptureUserActivityResult captureUserActivity(CaptureUserActivityCommand captureUserActivityCommand) {
        throw new UnsupportedOperationException();
    }
}
