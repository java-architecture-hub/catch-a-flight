package jah.catchflight.account.adapter.out;

import jah.catchflight.account.domain.model.*;
import jah.catchflight.account.port.out.CreateAccountRepository;
import jah.catchflight.account.port.out.FindAccountRepository;
import jah.catchflight.account.port.out.FindCurrentAccountRepository;
import jah.catchflight.account.port.out.UpdateAccountRepository;
import jah.catchflight.common.annotations.hexagonal.OutboundAdapter;
import jah.catchflight.sharedkernel.account.Email;
import jah.catchflight.sharedkernel.account.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Repository implementation for managing {@link Account} domain objects in the CatchFlight application.
 * This service acts as an outbound adapter in the hexagonal architecture, providing persistence operations
 * for accounts by interacting with the {@link AccountJdbcRepository} and mapping between domain and
 * persistence entities using {@link AccountJdbcEntityMapper}. It implements multiple repository interfaces
 * to support account creation, retrieval, and updates.
 */
@Service
@OutboundAdapter
@RequiredArgsConstructor
public class AccountRepository implements CreateAccountRepository, FindCurrentAccountRepository, FindAccountRepository, UpdateAccountRepository {
    private final AccountJdbcRepository accountJdbcRepository;
    private final AccountJdbcEntityMapper accountJdbcEntityMapper;

    /**Ī
     * Loads an account by its unique identifier.
     * Queries the database for an {@link AccountJdbcEntity} with the specified {@link UserId} and maps it to
     * a domain {@link Account} if found.
     */
    @Override
    public Optional<Account> load(UserId userId) {
        return accountJdbcRepository
                .findById(userId.value())
                .map(accountJdbcEntityMapper::toDomain);
    }

    /**
     * Finds a current account by its email address.
     * Queries the database for an {@link AccountJdbcEntity} with the specified email and returns a
     * {@link CurrentAccount} (either {@link RegularAccount} or {@link PremiumAccount}) if found,
     * or a {@link NonExistingAccount} if no account matches the email.
     */
    @Override
    public CurrentAccount findByEmail(Email email) {
        return accountJdbcRepository
                .findByEmail(email.value())
                .map(this::existingUser)
                .orElseGet(this::nonExistingUser);
    }

    /**
     * Creates a new account in the database.
     * Converts the provided domain {@link Account} to an {@link AccountJdbcEntity}, saves it to the database,
     * and returns the created {@link Account} mapped back to the domain model.
     */
    @Override
    public Account create(Account account) {
        var accountJdbcEntity = accountJdbcEntityMapper.toJdbcEntity(account);
        var createdJdbcEntity = accountJdbcRepository.save(accountJdbcEntity);
        return accountJdbcEntityMapper.toDomain(createdJdbcEntity);
    }

    /**
     * Saves an existing account to the database.
     * Converts the provided domain {@link Account} to an {@link AccountJdbcEntity} and persists it,
     * typically for updating existing account data.
     *
     * @param account the domain {@link Account} to save
     */
    @Override
    public void save(Account account) {
        accountJdbcRepository.save(accountJdbcEntityMapper.toJdbcEntity(account));
    }

    /**
     * Converts an {@link AccountJdbcEntity} to a {@link CurrentAccount} based on its account type.
     * Returns a {@link RegularAccount} or {@link PremiumAccount} depending on the account type of the entity.
     */
    private CurrentAccount existingUser(AccountJdbcEntity accountJdbcEntity) {
        return switch (accountJdbcEntity.accountType()) {
            case REGULAR -> new RegularAccount(new UserId(accountJdbcEntity.id()));
            case PREMIUM -> new PremiumAccount(new UserId(accountJdbcEntity.id()));
        };
    }

    /**
     * Creates a {@link NonExistingAccount} to represent the absence of an account.
     * Used when no account is found for a given email address.
     */
    private NonExistingAccount nonExistingUser() {
        return new NonExistingAccount();
    }
}
