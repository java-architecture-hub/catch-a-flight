package jah.catchflight.engagement.domain;

import jah.catchflight.common.annotations.domain.DomainService;
import jah.catchflight.engagement.port.in.CaptureUserActivityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * A service responsible for capturing user activity in the system.
 * <p>
 * This class implements the {@link CaptureUserActivityUseCase} interface to handle
 * the business logic for capturing user activity based on a provided command.
 * It is annotated as a Spring {@code @Service} and a domain service to indicate its
 * role in the application layer and domain logic.
 * </p>
 *
 * @see CaptureUserActivityUseCase
 * @see CaptureUserActivityCommand
 * @see CaptureUserActivityResult
 */
@Service
@DomainService
@RequiredArgsConstructor
public class CaptureUserActivityService implements CaptureUserActivityUseCase {

    /**
     * Captures user activity based on the provided command.
     * <p>
     * This method processes a {@link CaptureUserActivityCommand} to record user activity
     * and returns a {@link CaptureUserActivityResult} indicating the outcome.
     * </p>
     *
     * @param captureUserActivityCommand the command containing details of the user activity to capture
     * @return the result of the user activity capture operation
     * @throws UnsupportedOperationException if the operation is not yet implemented
     */
    @Override
    public CaptureUserActivityResult captureUserActivity(CaptureUserActivityCommand captureUserActivityCommand) {
        throw new UnsupportedOperationException();
    }
}

