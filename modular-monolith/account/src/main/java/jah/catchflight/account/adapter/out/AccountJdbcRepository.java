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
 */
@Repository
interface AccountJdbcRepository extends CrudRepository<AccountJdbcEntity, UUID> {
    Optional<AccountJdbcEntity> findByEmail(String email);
}
