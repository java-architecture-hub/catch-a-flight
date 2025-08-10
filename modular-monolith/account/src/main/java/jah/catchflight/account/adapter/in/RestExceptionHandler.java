package jah.catchflight.account.adapter.in;

import jah.catchflight.common.controller.ResponseBodyHelper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {
    /**
     * Handles validation errors for invalid method arguments.
     * This method processes {@code MethodArgumentNotValidException} thrown when request data fails validation.
     * It collects field-specific validation errors, formats them into a single error message, and logs the
     * failure with the request URI for debugging. The formatted error message is returned in a 400 Bad Request
     * response using {@code ResponseBodyHelper}.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleConflict(MethodArgumentNotValidException ex, WebRequest request) {
        // Format error message for response
        final String errorMessage = produceErrorMessage(ex);

        // Safely retrieve HttpServletRequest and request URI
        var httpRequest = request instanceof NativeWebRequest
                ? ((NativeWebRequest) request).getNativeRequest(HttpServletRequest.class)
                : null;

        String requestUri = httpRequest != null ? httpRequest.getRequestURI() : "unknown";
        log.error("Validation failed for request [{}]: {}", requestUri, errorMessage);
        return ResponseBodyHelper.badRequestBody(httpRequest, errorMessage);
    }

    /**
     * Formats validation errors into a single string message.
     */
    private String produceErrorMessage(MethodArgumentNotValidException ex) {
        // Collect validation errors into a map
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));

        // Format error message for response
        return errors.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining("; "));
    }
}
