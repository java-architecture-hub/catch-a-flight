package jah.catchflight.account.adapter.out;

import jah.catchflight.account.domain.model.Account;
import jah.catchflight.account.domain.model.Password;
import jah.catchflight.common.persistence.Version;
import jah.catchflight.sharedkernel.account.AccountId;
import jah.catchflight.sharedkernel.account.Email;
import jah.catchflight.sharedkernel.account.UserName;
import org.springframework.stereotype.Component;

/**
 * Maps between domain {@link Account} objects and persistence-layer {@link AccountJdbcEntity} objects
 * in the CatchFlight application. This class is responsible for converting domain models to JDBC entities
 * for database storage and vice versa, ensuring seamless data transformation between the domain and
 * persistence layers.
 */
@Component
class AccountJdbcEntityMapper {
    /**
     * Converts a domain {@link Account} object to a persistence-layer {@link AccountJdbcEntity}.
     * This method extracts relevant fields from the domain account, including user ID, email, password,
     * username, account type, and version, and constructs a corresponding JDBC entity for database storage.
     *
     * @param account the domain {@link Account} object to convert
     * @return an {@link AccountJdbcEntity} representing the account data for persistence
     */
    AccountJdbcEntity toJdbcEntity(Account account) {
        var userId = account.getAccountId();
        var userName = account.getUserName();
        var email = account.getEmail();
        var password = account.getPassword();
        var userType = account.getAccountType();
        var version = account.getVersion();

        return new AccountJdbcEntity(
                userId != null ? userId.value() : null,
                email.value(),
                password.value(),
                userName.firstName(),
                userName.lastName(),
                userType,
                version.value());
    }

    /**
     * Converts a persistence-layer {@link AccountJdbcEntity} to a domain {@link Account} object.
     * This method constructs domain value objects (e.g., {@link AccountId}, {@link Email}, {@link UserName},
     * {@link Password}, {@link Version}) from the JDBC entity fields and builds a domain {@link Account}
     * using the builder pattern.
     */
    Account toDomain(AccountJdbcEntity entity) {
        var userId = new AccountId(entity.id());
        var userName = new UserName(entity.firstName(), entity.lastName());
        var email = new Email(entity.email());
        var password = new Password(entity.password());
        var userType = entity.accountType();
        var version = new Version(entity.version());

        return Account.builder()
                .accountId(userId)
                .userName(userName)
                .email(email)
                .password(password)
                .accountType(userType)
                .version(version)
                .build();
    }
}
