package jah.catchflight.account.adapter.out;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for performing CRUD operations on {@link AccountJdbcEntity} in the CatchFlight application.
 * This interface extends {@link CrudRepository} to provide standard create, read, update, and delete operations
 * for account entities, using the {@link UUID} as the primary key. It also provides a custom query method to
 * find an account by email address.
 *
 * @since 1.0
 */
@Repository
interface AccountJdbcRepository extends CrudRepository<AccountJdbcEntity, UUID> {
    /**
     * Retrieves an account entity by its email address.
     * This method queries the database for an {@link AccountJdbcEntity} with the specified email.
     *
     * @param email the email address to search for
     * @return an {@link Optional} containing the {@link AccountJdbcEntity} if found, or an empty {@link Optional} if not
     * @since 1.0
     */
    Optional<AccountJdbcEntity> findByEmail(String email);
}
