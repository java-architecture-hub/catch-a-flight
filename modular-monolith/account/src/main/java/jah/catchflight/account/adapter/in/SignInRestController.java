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

@Slf4j
@InboundAdapter
@RestController
@RequiredArgsConstructor
class SignInRestController {
    private final SignInUseCase signInUseCase;
    private final SignInMapper signInMapper;
    private final HttpServletRequest servletRequest;

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

    record SignInRequest(String email, String password) {}

    static ResponseEntity<SignInResponse> successBody(UserId userId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new SuccessResponse(userId));
    }

    interface SignInResponse {
        record SuccessResponse(UserId userId) implements SignInResponse {}
    }

    private static class SignInMapper {
        SignInCommand toCommand(SignInRequest request) {
            return new SignInCommand();
        }
    }
}
