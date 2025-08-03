package jah.catchflight.account.adapter.in;

import jah.catchflight.account.port.in.UpgradeAccountUseCase;
import jah.catchflight.common.annotations.hexagonal.InboundAdapter;
import jah.catchflight.sharedkernel.account.UserId;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static jah.catchflight.account.port.in.UpgradeAccountUseCase.UpgradeUserCommand;
import static jah.catchflight.account.port.in.UpgradeAccountUseCase.UpgradeUserResult.*;

@Slf4j
@RestController
@InboundAdapter
@RequiredArgsConstructor
class UpgradeAccountRestController {
    private final UpgradeAccountUseCase upgradeAccountUseCase;
    private final UpgradeUserMapper upgradeUserMapper;
    private final HttpServletRequest servletRequest;

    @PostMapping
    ResponseEntity<?> upgrade(@PathVariable("accountId") String accountId) {
        log.info("Request, userId: {}", accountId);
        var upgradeUserCommand = upgradeUserMapper.toCommand(accountId);
        var upgradeUserResult = upgradeAccountUseCase.upgradeUser(upgradeUserCommand);
        return switch (upgradeUserResult) {
            case Success() -> successBody();
            case UserNotFoundFailure(String message) -> badRequestBody(servletRequest, message);
            case UserAlreadyUpgradedFailure(String message) -> badRequestBody(servletRequest, message);
            case InternalFailure(Throwable cause) -> internalServerErrorBody(servletRequest, cause);
        };
    }

    private static ResponseEntity<UpgradeUserResponse> successBody() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private ResponseEntity<?> badRequestBody(HttpServletRequest request, String message) {
        return ResponseEntity.badRequest().body(message);
    }

    private ResponseEntity<?> internalServerErrorBody(HttpServletRequest request, Throwable cause) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cause.getMessage());
    }

    private interface UpgradeUserResponse {
        record SuccessResponse() implements UpgradeUserResponse {}
    }

    private static class UpgradeUserMapper {
        UpgradeUserCommand toCommand(String userId) {
            return new UpgradeUserCommand(new UserId(UUID.fromString(userId)));
        }
    }
}
