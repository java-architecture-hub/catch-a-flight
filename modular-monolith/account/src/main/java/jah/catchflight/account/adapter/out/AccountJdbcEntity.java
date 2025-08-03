package jah.catchflight.account.adapter.out;

import jah.catchflight.sharedkernel.account.AccountType;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

/**
 * Represents a JDBC entity for storing account information in the CatchFlight application's database.
 * This record maps to the "ACCOUNTS" table and encapsulates the persistent attributes of a user account,
 * including the unique identifier, email, password, name, account type, and version for optimistic locking.
 *
 * @param id          the unique identifier of the account
 * @param email       the email address associated with the account
 * @param password    the hashed password for the account
 * @param firstName   the first name of the account holder
 * @param lastName    the last name of the account holder
 * @param accountType the type of account (e.g., REGULAR or PREMIUM)
 * @param version     the version number for optimistic locking
 * @since 1.0
 */
@Table(name = "ACCOUNTS")
public record AccountJdbcEntity(
        @Id UUID id,
        String email,
        String password,
        String firstName,
        String lastName,
        AccountType accountType,
        Integer version) {}
