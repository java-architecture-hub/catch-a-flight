package jah.catchflight.account.adapter.in;

import jah.catchflight.account.domain.model.Password;
import jah.catchflight.account.domain.model.UserName;
import jah.catchflight.account.port.in.CreateAccountUseCase;
import jah.catchflight.common.annotations.hexagonal.InboundAdapter;
import jah.catchflight.sharedkernel.account.Email;
import jah.catchflight.sharedkernel.account.UserId;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static jah.catchflight.account.port.in.CreateAccountUseCase.CreateAccountResult.*;
import static jah.catchflight.common.controller.ResponseBodyHelper.badRequestBody;
import static jah.catchflight.common.controller.ResponseBodyHelper.internalServerErrorBody;
import static org.springframework.http.ResponseEntity.status;

/**
 * REST controller for handling account creation requests in the CatchFlight application.
 * This controller serves as an inbound adapter in the hexagonal architecture, receiving
 * HTTP requests to create user accounts and delegating the processing to the
 * {@link CreateAccountUseCase}. It maps incoming requests to domain commands and
 * transforms use case results into appropriate HTTP responses.
 */
@Slf4j
@InboundAdapter
@RestController
@RequiredArgsConstructor
class CreateAccountRestController {
    private final CreateAccountUseCase createAccountUseCase;
    private final CreateAccountMapper createAccountMapper;
    private final HttpServletRequest servletRequest;

    /**
     * Handles HTTP POST requests to create a new user account.
     * This method validates the incoming request, maps it to a domain command,
     * invokes the account creation use case, and returns an appropriate HTTP response
     * based on the result.
     *
     * @param request the validated request containing user account details
     * @return a {@link ResponseEntity} containing the result of the account creation,
     * with HTTP status codes indicating success (201), bad request (400),
     * or internal server error (500)
     */
    @PostMapping
    ResponseEntity<?> createUser(@Validated @RequestBody CreateAccountRequest request) {
        log.info("Request: {}", request);
        var command = createAccountMapper.toCommand(request);
        var result = createAccountUseCase.createUser(command);
        return switch (result) {
            case Success(UserId userId) -> successBody(userId);
            case ExistingAccountFailure(String message) -> badRequestBody(servletRequest, message);
            case PasswordPolicyFailure(String message) -> badRequestBody(servletRequest, message);
            case InternalFailure(Throwable cause) -> internalServerErrorBody(servletRequest, cause);
        };
    }

    /**
     * Represents the request payload for creating a new user account.
     * This record encapsulates the required fields for account creation, including
     * email, password, first name, and last name, all of which are validated to ensure
     * they are not null.
     *
     * @param email     the email address of the user
     * @param password  the password for the user account
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     */
    record CreateAccountRequest(
            @NotNull String email,
            @NotNull String password,
            @NotNull String firstName,
            @NotNull String lastName) {}

    /**
     * Defines the response structure for account creation operations.
     * This sealed interface provides a type-safe way to represent successful
     * account creation responses.
     */
    interface CreateAccountResponse {
        record SuccessResponse(UserId userId) implements CreateAccountResponse {}
    }

    /**
     * Constructs a successful HTTP response for account creation.
     * Returns a 201 Created status with the user ID in the response body.
     *
     * @param userId the unique identifier of the created user
     * @return a {@link ResponseEntity} with HTTP status 201 and a {@link CreateAccountResponse}
     */
    private static ResponseEntity<CreateAccountResponse> successBody(UserId userId) {
        return status(HttpStatus.CREATED).body(new CreateAccountResponse.SuccessResponse(userId));
    }

    /**
     * Maps HTTP requests to domain commands for account creation.
     * This class is responsible for transforming a {@link CreateAccountRequest}
     * into a {@link CreateAccountUseCase.CreateAccountCommand} for use by the
     * account creation use case.
     */
    private static class CreateAccountMapper {
        CreateAccountUseCase.CreateAccountCommand toCommand(CreateAccountRequest request) {
            return new CreateAccountUseCase.CreateAccountCommand(
                    new Email(request.email()),
                    new Password(request.password()),
                    new UserName(request.firstName(), request.lastName()));
        }
    }
}
