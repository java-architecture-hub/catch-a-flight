package jah.catchflight.account.adapter.in;

import jah.catchflight.account.port.in.UpgradeAccountUseCase;
import jah.catchflight.common.annotations.hexagonal.InboundAdapter;
import jah.catchflight.sharedkernel.account.AccountId;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static jah.catchflight.account.adapter.in.RestResources.UPGRADE_ACCOUNT_API;
import static jah.catchflight.account.port.in.UpgradeAccountUseCase.UpgradeUserCommand;
import static jah.catchflight.account.port.in.UpgradeAccountUseCase.UpgradeUserResult.*;
import static jah.catchflight.common.controller.ResponseBodyHelper.badRequestBody;
import static jah.catchflight.common.controller.ResponseBodyHelper.internalServerErrorBody;

/**
 * REST controller for handling account upgrade requests in the CatchFlight application.
 * This controller serves as an inbound adapter in the hexagonal architecture, receiving
 * HTTP requests to upgrade user accounts and delegating the processing to the
 * {@link UpgradeAccountUseCase}. It maps incoming requests to domain commands and
 * transforms use case results into appropriate HTTP responses.
 */
@Slf4j
@RestController
@InboundAdapter
@RequiredArgsConstructor
@RequestMapping(UPGRADE_ACCOUNT_API)
class UpgradeAccountRestController {
    private final UpgradeAccountUseCase upgradeAccountUseCase;
    private final UpgradeUserMapper upgradeUserMapper;
    private final HttpServletRequest servletRequest;

    /**
     * Constructs a successful HTTP response for account upgrade.
     * Returns a 201 Created status with no response body.
     */
    private static ResponseEntity<Void> successBody() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Handles HTTP POST requests to upgrade a user account.
     * This method processes the account ID from the path variable, maps it to a domain command,
     * invokes the upgrade use case, and returns an appropriate HTTP response based on the result.
     *
     * @param accountId the unique identifier of the account to upgrade, provided as a path variable
     * @return a {@link ResponseEntity} containing the result of the account upgrade,
     * with HTTP status codes indicating success (201), bad request (400) for
     * user not found or already upgraded failures, or internal server error (500)
     */
    @PostMapping
    ResponseEntity<?> upgrade(@PathVariable("accountId") String accountId) {
        log.info("Request, userId: {}", accountId);
        var upgradeUserCommand = upgradeUserMapper.toCommand(accountId);
        var upgradeUserResult = upgradeAccountUseCase.upgradeUser(upgradeUserCommand);
        return switch (upgradeUserResult) {
            case Success() -> successBody();
            case AccountNotFoundFailure(String message) -> badRequestBody(servletRequest, message);
            case AccountAlreadyUpgradedFailure(String message) -> badRequestBody(servletRequest, message);
            case InternalFailure(Throwable cause) -> internalServerErrorBody(servletRequest, cause);
        };
    }

    /**
     * Maps HTTP request data to domain commands for account upgrade operations.
     * This class is responsible for transforming an account ID into a
     * {@link UpgradeUserCommand} for use by the upgrade use case.
     */
    @Component
    private static class UpgradeUserMapper {
        UpgradeUserCommand toCommand(String userId) {
            return new UpgradeUserCommand(new AccountId(UUID.fromString(userId)));
        }
    }
}
