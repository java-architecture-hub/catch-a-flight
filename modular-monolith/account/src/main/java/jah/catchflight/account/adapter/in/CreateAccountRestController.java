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

@Slf4j
@InboundAdapter
@RestController
@RequiredArgsConstructor
class CreateAccountRestController {
    private final CreateAccountUseCase createAccountUseCase;
    private final CreateAccountMapper createAccountMapper;
    private final HttpServletRequest servletRequest;

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

    record CreateAccountRequest(
            @NotNull String email,
            @NotNull String password,
            @NotNull String firstName,
            @NotNull String lastName) {}

    interface CreateAccountResponse {
        record SuccessResponse(UserId userId) implements CreateAccountResponse {}
    }

    private static ResponseEntity<CreateAccountResponse> successBody(UserId userId) {
        return status(HttpStatus.CREATED).body(new CreateAccountResponse.SuccessResponse(userId));
    }

    private static class CreateAccountMapper {
        CreateAccountUseCase.CreateAccountCommand toCommand(CreateAccountRequest request) {
            return new CreateAccountUseCase.CreateAccountCommand(
                    new Email(request.email()),
                    new Password(request.password()),
                    new UserName(request.firstName(), request.lastName()));
        }
    }
}
