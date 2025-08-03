package jah.catchflight.account.adapter.out;

import jah.catchflight.account.domain.model.Account;
import jah.catchflight.account.domain.model.Password;
import jah.catchflight.account.domain.model.UserName;
import jah.catchflight.common.persistence.Version;
import jah.catchflight.sharedkernel.account.Email;
import jah.catchflight.sharedkernel.account.UserId;

class AccountJdbcEntityMapper {
    AccountJdbcEntity toJdbcEntity(Account account) {
        var userId = account.getUserId();
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

    Account toDomain(AccountJdbcEntity entity) {
        var userId = new UserId(entity.id());
        var userName = new UserName(entity.firstName(), entity.lastName());
        var email = new Email(entity.email());
        var password = new Password(entity.password());
        var userType = entity.accountType();
        var version = new Version(entity.version());

        return Account.builder()
                .userId(userId)
                .userName(userName)
                .email(email)
                .password(password)
                .accountType(userType)
                .version(version)
                .build();
    }
}

