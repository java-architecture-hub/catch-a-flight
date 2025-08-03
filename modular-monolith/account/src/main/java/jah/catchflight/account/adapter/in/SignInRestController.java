package jah.catchflight.account.adapter.in;

import jah.catchflight.account.port.in.SignInUseCase;
import jah.catchflight.common.annotations.hexagonal.InboundAdapter;
import jah.catchflight.sharedkernel.account.UserId;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static jah.catchflight.account.adapter.in.SignInRestController.SignInResponse.SuccessResponse;
import static jah.catchflight.account.port.in.SignInUseCase.SignInCommand;
import static jah.catchflight.account.port.in.SignInUseCase.SignInResult.*;
import static jah.catchflight.common.controller.ResponseBodyHelper.badRequestBody;
import static jah.catchflight.common.controller.ResponseBodyHelper.internalServerErrorBody;

/**
 * REST controller for handling user sign-in requests in the CatchFlight application.
 * This controller serves as an inbound adapter in the hexagonal architecture, receiving
 * HTTP requests for user authentication and delegating the processing to the
 * {@link SignInUseCase}. It maps incoming requests to domain commands and transforms
 * use case results into appropriate HTTP responses.
 */
@Slf4j
@InboundAdapter
@RestController
@RequiredArgsConstructor
class SignInRestController {
    /**
     * The use case responsible for executing the sign-in logic.
     */
    private final SignInUseCase signInUseCase;

    /**
     * The mapper responsible for converting HTTP requests to domain commands.
     */
    private final SignInMapper signInMapper;

    /**
     * The HTTP servlet request, used for constructing error responses with context.
     */
    private final HttpServletRequest servletRequest;

    /**
     * Handles HTTP POST requests to authenticate a user.
     * This method validates the incoming sign-in request, maps it to a domain command,
     * invokes the sign-in use case, and returns an appropriate HTTP response based on
     * the result.
     *
     * @param request the validated request containing user credentials (email and password)
     * @return a {@link ResponseEntity} containing the result of the sign-in operation,
     *         with HTTP status codes indicating success (201), bad request (400) for
     *         authentication failure, or internal server error (500)
     */
    @PostMapping
    ResponseEntity<?> signInUser(@Validated @RequestBody SignInRequest request) {
        log.info("Request: {}", request);
        var command = signInMapper.toCommand(request);
        var result = signInUseCase.signIn(command);
        return switch (result) {
            case Success(UserId userId) -> successBody(userId);
            case AuthenticationFailure _ -> badRequestBody(servletRequest, "Incorrect user and/or password.");
            case InternalFailure(Throwable cause) -> internalServerErrorBody(servletRequest, cause);
        };
    }

    /**
     * Represents the request payload for user sign-in.
     * This record encapsulates the required fields for authentication, including
     * the user's email and password.
     *
     * @param email    the email address of the user
     * @param password the password for the user account
     */
    record SignInRequest(String email, String password) {}

    /**
     * Constructs a successful HTTP response for user sign-in.
     * Returns a 201 Created status with the user ID in the response body.
     *
     * @param userId the unique identifier of the authenticated user
     * @return a {@link ResponseEntity} with HTTP status 201 and a {@link SignInResponse}
     */
    static ResponseEntity<SignInResponse> successBody(UserId userId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new SuccessResponse(userId));
    }

    /**
     * Defines the response structure for sign-in operations.
     * This sealed interface provides a type-safe way to represent successful
     * sign-in responses.
     */
    interface SignInResponse {
        /**
         * Represents a successful sign-in response.
         * Contains the unique identifier of the authenticated user.
         *
         * @param userId the unique identifier of the authenticated user
         */
        record SuccessResponse(UserId userId) implements SignInResponse {}
    }

    /**
     * Maps HTTP requests to domain commands for sign-in operations.
     * This class is responsible for transforming a {@link SignInRequest}
     * into a {@link SignInCommand} for use by the sign-in use case.
     */
    private static class SignInMapper {
        /**
         * Converts a {@link SignInRequest} to a {@link SignInCommand}.
         * Maps the request fields to a domain command for authentication.
         *
         * @param request the HTTP request containing sign-in credentials
         * @return a {@link SignInCommand} for the use case
         */
        SignInCommand toCommand(SignInRequest request) {
            return new SignInCommand();
        }
    }
}
