package jah.catchflight.account.adapter.out;

import jah.catchflight.sharedkernel.account.AccountType;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "ACCOUNTS")
public record AccountJdbcEntity(
        @Id UUID id,
        String email,
        String password,
        String firstName,
        String lastName,
        AccountType accountType,
        Integer version) {}
